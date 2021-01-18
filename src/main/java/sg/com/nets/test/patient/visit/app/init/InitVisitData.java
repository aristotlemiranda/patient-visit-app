package sg.com.nets.test.patient.visit.app.init;

import java.text.ParseException;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import sg.com.nets.test.patient.visit.app.config.ConfigProperty;
import sg.com.nets.test.patient.visit.app.entity.Physician;
import sg.com.nets.test.patient.visit.app.entity.Visit;
import sg.com.nets.test.patient.visit.app.model.StringConstant;
import sg.com.nets.test.patient.visit.app.repository.VisitNeo4jRepo;
import sg.com.nets.test.patient.visit.app.repository.VisitRepo;

/**
 * @author Miranda Aristotle Mungcal
 * 
 **/

@Component
public class InitVisitData {

	@Autowired
	private VisitNeo4jRepo neo4jrepo;

	@Autowired
	private VisitRepo repo;
	
	@Autowired
	@Qualifier("configMapper")
	private ConfigProperty property;

	@PostConstruct
	void createVisitData() throws ParseException {

		Physician doc = new Physician();
		doc.setId(100);
		doc.setCreatedBy("admin");
		doc.setCreatedDateTime(Calendar.getInstance().getTime());
		doc.setModifiedDateTime(Calendar.getInstance().getTime());
		doc.setName("Dr. DoLittle");
		
		Visit visit = new Visit();
		visit.setId(1);
		visit.setCreatedBy("admin");
		visit.setCreatedDateTime(Calendar.getInstance().getTime());
		visit.setModifiedDateTime(Calendar.getInstance().getTime());
		visit.setReason("Stomachache");
		visit.setPhysician(doc);
		
		final String databeType = property.getConfig().get("database-type");
		if (StringConstant.DB_NEO4J.equals(databeType)) {
			
			try {
				neo4jrepo.findById(visit.getId()).get();
			} catch (Exception e) {
				neo4jrepo.save(visit);
			}
		} 
		if (StringConstant.DB_MYSQL.equals(databeType)) {
			
			try {
				repo.findById(visit.getId()).get();
			} catch (Exception e) {
				repo.save(visit);
			}
		}
	}
}
