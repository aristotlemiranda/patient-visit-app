package sg.com.nets.test.patient.visit.app.model;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Miranda Aristotle Mungcal
 * 
 **/
public enum Gender {
	
	M("M"), F("F");
	
	private String code;
	
	private Gender(String code) {
		this.code = code;
	}
	
	public static Gender decode(final String gender) {
		return Stream.of(Gender.values()).filter(e -> e.code.equals(gender)).findFirst().orElse(null);
	}
	
	@JsonValue
	public String getCode() {
		return code;
	}
}
