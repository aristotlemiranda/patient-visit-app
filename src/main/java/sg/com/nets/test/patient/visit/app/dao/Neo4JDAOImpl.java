package sg.com.nets.test.patient.visit.app.dao;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sg.com.nets.test.patient.visit.app.entity.Holiday;
import sg.com.nets.test.patient.visit.app.entity.Patient;
import sg.com.nets.test.patient.visit.app.entity.Physician;
import sg.com.nets.test.patient.visit.app.entity.Visit;
import sg.com.nets.test.patient.visit.app.exception.PatientVisitException;
import sg.com.nets.test.patient.visit.app.repository.HolidayNeo4jRepo;
import sg.com.nets.test.patient.visit.app.repository.PatientNeo4jRepo;
import sg.com.nets.test.patient.visit.app.repository.PhysicianNeo4jRepo;
import sg.com.nets.test.patient.visit.app.repository.VisitNeo4jRepo;

/**
 * @author Miranda Aristotle Mungcal
 * 
 **/

@Repository("noSQLRepo")
@Transactional(transactionManager = "chainedTractionMgr")
public class Neo4JDAOImpl implements PatientVisitDAO {
	
	@Autowired
	PatientNeo4jRepo patientNeo4jRepo;
	
	@Autowired
	PhysicianNeo4jRepo physicianNeo4jRepo;
	
	@Autowired
	VisitNeo4jRepo visitNeo4jRepo;

	@Autowired
	HolidayNeo4jRepo holidayNeo4jRepo;

	@Override
	public Patient findPatientById(int id) {
		try {
			return patientNeo4jRepo.findById(id).get();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Physician findPhysicianById(int id) {
		try {
			return physicianNeo4jRepo.findById(id).get();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Holiday findHolidayByName(String name) {
		return holidayNeo4jRepo.findHolidayByName(name);
	}

	@Override
	public Visit findVisitById(int id) {
		try {
			return visitNeo4jRepo.findById(id).get();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Holiday> findHolidayByHolidayDate() throws PatientVisitException {
		
		 SimpleDateFormat formatter = new SimpleDateFormat(
			      "yyyy-MM-dd");
		
		List<Holiday> holidays;
		
		holidays = holidayNeo4jRepo.findAll().stream().filter(i -> formatter.format(i.getHolidayDate()).equals(formatter.format(Calendar.getInstance().getTime()))).collect(Collectors.toList());
		
		holidays.forEach(i -> {
			System.out.println(i.getName() + " holiday date:" + i.getHolidayDate());
		});
		
		return holidays;
	}


	@Override
	public Patient savePatient(Patient patient) {
		return patientNeo4jRepo.save(patient);
	}



	@Override
	public Physician savePhysician(Physician physician) {
		return physicianNeo4jRepo.save(physician);
	}



	@Override
	public Holiday saveHoliday(Holiday holiday) { 
		return holidayNeo4jRepo.save(holiday);
	}



	@Override
	public Visit saveVisit(Visit visit) {
		return visitNeo4jRepo.save(visit);
	}


	@Override
	public void deletePatient(Patient patient) {
		patientNeo4jRepo.delete(patient);
	}



	@Override
	public void deletePhysician(Physician physician) {
		physicianNeo4jRepo.delete(physician);
	}



	@Override
	public void deleteHoliday(Holiday holiday) {
		holidayNeo4jRepo.delete(holiday);
	}



	@Override
	public void deleteVisit(Visit visit) {
		 visitNeo4jRepo.delete(visit);
	}


}
