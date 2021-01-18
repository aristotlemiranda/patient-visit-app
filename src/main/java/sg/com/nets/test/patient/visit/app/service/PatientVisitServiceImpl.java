package sg.com.nets.test.patient.visit.app.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sg.com.nets.test.patient.visit.app.dao.DAOFactory;
import sg.com.nets.test.patient.visit.app.entity.Holiday;
import sg.com.nets.test.patient.visit.app.entity.Patient;
import sg.com.nets.test.patient.visit.app.entity.Physician;
import sg.com.nets.test.patient.visit.app.entity.Visit;
import sg.com.nets.test.patient.visit.app.exception.PatientVisitException;

/**
 * @author Miranda Aristotle Mungcal
 * 
 **/

@Service
public class PatientVisitServiceImpl implements PatientVisitService {

	@Autowired
	@Qualifier("daoFactory")
	DAOFactory factory;
	

	@Override
	public Patient findPatientById(int id) throws PatientVisitException{
		return factory.getDAO().findPatientById(id);
	}

	@Override
	public Physician findPhysicianById(int id) throws PatientVisitException{
		return factory.getDAO().findPhysicianById(id);
	}

	@Override
	public Holiday findHolidayByName(String name) throws PatientVisitException{
		return factory.getDAO().findHolidayByName(name);
	}

	@Override
	public Visit findVisitById(int id) throws PatientVisitException{
		return factory.getDAO().findVisitById(id);
	}
	
	public List<Holiday> findHolidayByHolidayDate(){
		return factory.getDAO().findHolidayByHolidayDate();
	}

	@Override
	public Patient savePatient(Patient patient) throws PatientVisitException{
		return factory.getDAO().savePatient(patient);
	}

	@Override
	public Physician savePhysician(Physician physician) throws PatientVisitException {
		return factory.getDAO().savePhysician(physician);
	}

	@Override
	public Holiday saveHoliday(Holiday holiday) throws PatientVisitException{
		return factory.getDAO().saveHoliday(holiday);
	}

	@Override
	public Visit saveVisit(Visit visit) throws PatientVisitException{
		return factory.getDAO().saveVisit(visit);
	}

	@Override
	public void deletePatient(Patient patient) throws PatientVisitException{
		factory.getDAO().deletePatient(patient);
	}

	@Override
	public void deletePhysician(Physician physician) throws PatientVisitException{
		factory.getDAO().deletePhysician(physician);
	}

	@Override
	public void deleteHoliday(Holiday holiday) throws PatientVisitException{
		factory.getDAO().deleteHoliday(holiday);
	}

	@Override
	public void deleteVisit(Visit visit) throws PatientVisitException{
		factory.getDAO().deleteVisit(visit);
	}
}
