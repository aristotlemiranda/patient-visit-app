package sg.com.nets.test.patient.visit.app.service;

import java.util.List;

import sg.com.nets.test.patient.visit.app.entity.Holiday;
import sg.com.nets.test.patient.visit.app.entity.Patient;
import sg.com.nets.test.patient.visit.app.entity.Physician;
import sg.com.nets.test.patient.visit.app.entity.Visit;
import sg.com.nets.test.patient.visit.app.exception.PatientVisitException;

/**
 * @author Miranda Aristotle Mungcal
 * 
 **/
public interface PatientVisitService {
	
	public Patient findPatientById(int id) throws PatientVisitException;
	public Physician findPhysicianById(int id) throws PatientVisitException;
	public Holiday findHolidayByName(String name) throws PatientVisitException;
	public Visit findVisitById(int id) throws PatientVisitException;
	public List<Holiday>  findHolidayByHolidayDate() throws PatientVisitException;
	
	public Patient savePatient(Patient patient) throws PatientVisitException;
	public Physician savePhysician(Physician physician) throws PatientVisitException;
	public Holiday saveHoliday(Holiday holiday) throws PatientVisitException;
	public Visit saveVisit(Visit visit) throws PatientVisitException;
	
	public void deletePatient(Patient patient) throws PatientVisitException;
	public void deletePhysician(Physician physician) throws PatientVisitException;
	public void deleteHoliday(Holiday holiday) throws PatientVisitException;
	public void deleteVisit(Visit visit) throws PatientVisitException;
}
