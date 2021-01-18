package sg.com.nets.test.patient.visit.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.com.nets.test.patient.visit.app.entity.Patient;

/**
 * @author Miranda Aristotle Mungcal
 * 
 **/
public interface PatientRepo extends JpaRepository<Patient, Integer>{

}
