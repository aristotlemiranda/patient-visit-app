package sg.com.nets.test.patient.visit.app.init;

import java.text.ParseException;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import sg.com.nets.test.patient.visit.app.config.ConfigProperty;
import sg.com.nets.test.patient.visit.app.entity.Physician;
import sg.com.nets.test.patient.visit.app.model.StringConstant;
import sg.com.nets.test.patient.visit.app.repository.PhysicianNeo4jRepo;
import sg.com.nets.test.patient.visit.app.repository.PhysicianRepo;

/**
 * @author Miranda Aristotle Mungcal
 * 
 **/
@Component
public class InitPhysicianData {

	@Autowired
	private PhysicianNeo4jRepo neo4jrepo;

	@Autowired
	private PhysicianRepo repo;

	@Autowired
	@Qualifier("configMapper")
	private ConfigProperty property;

	@PostConstruct
	void createPhysicianData() throws ParseException {

		Physician doctor = new Physician();
		doctor.setId(1);
		doctor.setCreatedBy("admin");
		doctor.setCreatedDateTime(Calendar.getInstance().getTime());
		doctor.setModifiedDateTime(Calendar.getInstance().getTime());
		doctor.setName("Dr. Quack Quack");
		
		final String databeType = property.getConfig().get("database-type");
		
		if (StringConstant.DB_NEO4J.equals(databeType)) {
			
			try {
				neo4jrepo.findById(doctor.getId()).get();
			} catch (Exception e) {
				neo4jrepo.save(doctor);
			}
		} 
		if (StringConstant.DB_MYSQL.equals(databeType)) {
			
			try {
				repo.findById(doctor.getId()).get();
			} catch (Exception e) {
				repo.save(doctor);
			}
		}
	}
}
