package sg.com.nets.test.patient.visit.app.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sg.com.nets.test.patient.visit.app.entity.Holiday;
import sg.com.nets.test.patient.visit.app.entity.Patient;
import sg.com.nets.test.patient.visit.app.entity.Physician;
import sg.com.nets.test.patient.visit.app.entity.Visit;
import sg.com.nets.test.patient.visit.app.repository.HolidayRepo;
import sg.com.nets.test.patient.visit.app.repository.PatientRepo;
import sg.com.nets.test.patient.visit.app.repository.PhysicianRepo;
import sg.com.nets.test.patient.visit.app.repository.VisitRepo;

/**
 * @author Miranda Aristotle Mungcal
 * */

@Repository("mysqlJPA")
@Transactional(transactionManager = "chainedTractionMgr")
public class MySQLDAOImpl implements PatientVisitDAO {


	@Autowired
	PatientRepo patientRepo;
	
	@Autowired
	PhysicianRepo physicianRepo;
	
	@Autowired
	VisitRepo visitRepo;

	@Autowired
	HolidayRepo holidayRepo;

	@Override
	public Patient findPatientById(int id) {
		Optional<Patient> patient = patientRepo.findById(id);
		try {
			return patient.get();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Physician findPhysicianById(int id) {
		Optional<Physician> physician = physicianRepo.findById(id);
		try {
			return physician.get(); 
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Holiday findHolidayByName(String name) {
		return holidayRepo.findHolidayByName(name);
	}

	@Override
	public Visit findVisitById(int id) {
		Optional<Visit> visit = visitRepo.findById(id);
		try {
			return visit.get();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Holiday> findHolidayByHolidayDate() {
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);  
		List<Holiday> holidays = new ArrayList<>();
		System.out.println(date);
		holidays = holidayRepo.findHolidayByHolidayDate(date);
		
		return holidays;
	}


	@Override
	public Patient savePatient(Patient patient) {
		return patientRepo.save(patient);
	}



	@Override
	public Physician savePhysician(Physician physician) {
		return physicianRepo.save(physician);
	}



	@Override
	public Holiday saveHoliday(Holiday holiday) { 
		return holidayRepo.save(holiday);
	}



	@Override
	public Visit saveVisit(Visit visit) {
		return visitRepo.save(visit);
	}


	@Override
	public void deletePatient(Patient patient) {
		patientRepo.delete(patient);
	}



	@Override
	public void deletePhysician(Physician physician) {
		physicianRepo.delete(physician);
	}



	@Override
	public void deleteHoliday(Holiday holiday) {
		holidayRepo.delete(holiday);
	}



	@Override
	public void deleteVisit(Visit visit) {
		 visitRepo.delete(visit);
	}


}
