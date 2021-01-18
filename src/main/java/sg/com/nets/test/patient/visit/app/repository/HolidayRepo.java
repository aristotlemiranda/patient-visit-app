package sg.com.nets.test.patient.visit.app.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import sg.com.nets.test.patient.visit.app.entity.Holiday;

/**
 * @author Miranda Aristotle Mungcal
 * 
 **/
public interface HolidayRepo extends JpaRepository<Holiday, String>{
	public List<Holiday> findHolidayByHolidayDate(Date holidayDate);
	public Holiday findHolidayByName(String name);
}
