package sg.com.nets.test.patient.visit.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sg.com.nets.test.patient.visit.app.entity.Physician;

/**
 * @author Miranda Aristotle Mungcal
 * 
 **/
public interface PhysicianRepo extends JpaRepository<Physician, Integer>{

}
