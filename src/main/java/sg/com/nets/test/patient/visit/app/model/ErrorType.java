package sg.com.nets.test.patient.visit.app.model;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Miranda Aristotle Mungcal
 * 
 **/
public enum ErrorType {

	DUPLICATE_ID("Duplicate id, constraint [entity.PRIMARY]"),
	NO_RECORD_FOUND("Record not found."),
	ACTION_NOT_ALLOWED_DURING_HOLIDAY("Current Date is a Holiday. This operation is not allowable.");
	
	private String code;
	
	private ErrorType(String code) {
		this.code = code;
	}
	
	public static ErrorType decode(final String error) {
		return Stream.of(ErrorType.values()).filter(e -> e.code.equals(error)).findFirst().orElse(null);
	}
	
	@JsonValue
	public String getCode() {
		return code;
	}
}
