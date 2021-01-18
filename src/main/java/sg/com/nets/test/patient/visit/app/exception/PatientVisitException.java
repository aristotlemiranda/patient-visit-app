package sg.com.nets.test.patient.visit.app.exception;

import org.springframework.dao.DataIntegrityViolationException;

/**
 * @author Miranda Aristotle Mungcal
 * 
 **/

public class PatientVisitException extends DataIntegrityViolationException {


	private static final long serialVersionUID = -85600237744736455L;
	
	public PatientVisitException(String message) {
		super(message);
	}
}
