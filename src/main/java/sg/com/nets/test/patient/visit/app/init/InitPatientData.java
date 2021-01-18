package sg.com.nets.test.patient.visit.app.init;

import java.text.ParseException;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import sg.com.nets.test.patient.visit.app.config.ConfigProperty;
import sg.com.nets.test.patient.visit.app.entity.Patient;
import sg.com.nets.test.patient.visit.app.model.Gender;
import sg.com.nets.test.patient.visit.app.model.StringConstant;
import sg.com.nets.test.patient.visit.app.repository.PatientNeo4jRepo;
import sg.com.nets.test.patient.visit.app.repository.PatientRepo;

/**
 * @author Miranda Aristotle Mungcal
 * 
 **/

@Component
public class InitPatientData {

	@Autowired
	private PatientNeo4jRepo neo4jrepo;

	@Autowired
	private PatientRepo repo;

	@Autowired
	@Qualifier("configMapper")
	private ConfigProperty property;

	@PostConstruct
	void createPatientData() throws ParseException {
		
		Patient patient = new Patient();
		patient.setAge(25);
		patient.setCreatedBy("admin");
		patient.setCreatedDateTime(Calendar.getInstance().getTime());
		patient.setModifiedDateTime(Calendar.getInstance().getTime());
		patient.setGender(Gender.M);
		patient.setName("Mao Shan Wang");
		patient.setId(1);
		
		
		final String databeType = property.getConfig().get("database-type");
		if (StringConstant.DB_NEO4J.equals(databeType)) {
			
			try {
				 neo4jrepo.findById(patient.getId()).get();
			} catch (Exception e) {
				neo4jrepo.save(patient);
			}
		} 
		if (StringConstant.DB_MYSQL.equals(databeType)) {
			try {
				repo.findById(patient.getId()).get();
			} catch (Exception e) {
				repo.save(patient);
			}
		}
	}
}
