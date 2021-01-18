package sg.com.nets.test.patient.visit.app.model;

import org.springframework.http.HttpStatus;

/**
 * @author Miranda Aristotle Mungcal
 * 
 **/

public class PatientVisitError {
	
	private String errorMessage;
	private HttpStatus status;
	
	public PatientVisitError(String errorMessage, HttpStatus status) {
		this.errorMessage = errorMessage;
		this.status = status;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	
}
