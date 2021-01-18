package sg.com.nets.test.patient.visit.app.init;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import sg.com.nets.test.patient.visit.app.config.ConfigProperty;
import sg.com.nets.test.patient.visit.app.entity.Holiday;
import sg.com.nets.test.patient.visit.app.model.StringConstant;
import sg.com.nets.test.patient.visit.app.repository.HolidayNeo4jRepo;
import sg.com.nets.test.patient.visit.app.repository.HolidayRepo;

/**
 * @author Miranda Aristotle Mungcal
 * 
 **/

@Component
public class InitHolidayData {

	@Autowired
	private HolidayNeo4jRepo neo4jrepo;

	@Autowired
	private HolidayRepo repo;

	@Autowired
	@Qualifier("configMapper")
	private ConfigProperty property;

	@PostConstruct
	void createHistoryData() throws ParseException {

		Holiday xmas = new Holiday();
		xmas.setName("CHRISTMAS");
		xmas.setCreatedBy("admin");
		xmas.setCreatedDateTime(Calendar.getInstance().getTime());
		xmas.setModifiedDateTime(Calendar.getInstance().getTime());
		xmas.setHolidayDate(new GregorianCalendar(2020, Calendar.DECEMBER, 15).getTime());

		Holiday cny = new Holiday();
		cny.setName("CHINESE NEW YEAR");
		cny.setCreatedBy("admin");
		cny.setCreatedDateTime(Calendar.getInstance().getTime());
		cny.setModifiedDateTime(Calendar.getInstance().getTime());
		cny.setHolidayDate(new GregorianCalendar(2021, Calendar.FEBRUARY, 12).getTime());

		/**
		 * Uncomment below line to insert a holiday today.
		 * 
		 * Holiday today = new Holiday(); today.setName("TODAY IS THE DAY");
		 * today.setCreatedBy("admin");
		 * today.setCreatedDateTime(Calendar.getInstance().getTime());
		 * today.setModifiedDateTime(Calendar.getInstance().getTime());
		 * today.setHolidayDate(Calendar.getInstance().getTime());
		 * 
		 */
		final String databeType = property.getConfig().get("database-type");
		
		if (StringConstant.DB_NEO4J.equals(databeType)) {
			neo4jrepo.deleteAll();
			neo4jrepo.save(xmas);
			neo4jrepo.save(cny);
			// neo4jrepo.save(today);
		} 
		if (StringConstant.DB_MYSQL.equals(databeType)) {
			repo.deleteAll();
			repo.save(xmas);
			repo.save(cny);
			// repo.save(today);
		}
	}
}
