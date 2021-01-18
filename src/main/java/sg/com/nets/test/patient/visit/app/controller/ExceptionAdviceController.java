package sg.com.nets.test.patient.visit.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import sg.com.nets.test.patient.visit.app.exception.PatientVisitException;
import sg.com.nets.test.patient.visit.app.model.ErrorType;
import sg.com.nets.test.patient.visit.app.model.PatientVisitError;

/***
 *@author Miranda Aristotle
 **/

@ControllerAdvice
public class ExceptionAdviceController {
	
	@ExceptionHandler(PatientVisitException.class)
	public ResponseEntity<PatientVisitError> handleException(PatientVisitException ex) {
		
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		
		if(ErrorType.DUPLICATE_ID.getCode().equals(ex.getMessage()) ) {
			status = HttpStatus.ALREADY_REPORTED;
		}
		
		if(ErrorType.ACTION_NOT_ALLOWED_DURING_HOLIDAY.getCode().equals(ex.getMessage())
				|| ErrorType.NO_RECORD_FOUND.getCode().equals(ex.getMessage())) {
			
			status = HttpStatus.BAD_REQUEST;
		}
		  
		PatientVisitError error = new PatientVisitError(ex.getMessage(), status);
		return new ResponseEntity<PatientVisitError>(error ,status);	
	}
}
