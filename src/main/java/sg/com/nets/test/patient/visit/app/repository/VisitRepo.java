package sg.com.nets.test.patient.visit.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sg.com.nets.test.patient.visit.app.entity.Visit;

/**
 * @author Miranda Aristotle Mungcal
 * 
 **/
public interface VisitRepo extends JpaRepository<Visit, Integer>{

}
