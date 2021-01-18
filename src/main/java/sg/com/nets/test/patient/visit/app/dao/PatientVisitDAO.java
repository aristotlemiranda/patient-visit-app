package sg.com.nets.test.patient.visit.app.dao;


import java.util.List;
import sg.com.nets.test.patient.visit.app.entity.Holiday;
import sg.com.nets.test.patient.visit.app.entity.Patient;
import sg.com.nets.test.patient.visit.app.entity.Physician;
import sg.com.nets.test.patient.visit.app.entity.Visit;

/**
 * @author Miranda Aristotle Mungcal
 * 
 **/

public interface PatientVisitDAO  {

	public Patient findPatientById(int id);
	public Physician findPhysicianById(int id);
	public Holiday findHolidayByName(String name);
	public Visit findVisitById(int id);
	public List<Holiday>  findHolidayByHolidayDate();
	
	public Patient savePatient(Patient patient);
	public Physician savePhysician(Physician physician);
	public Holiday saveHoliday(Holiday holiday);
	public Visit saveVisit(Visit visit);
	
	public void deletePatient(Patient patient);
	public void deletePhysician(Physician physician);
	public void deleteHoliday(Holiday holiday);
	public void deleteVisit(Visit visit);
}
