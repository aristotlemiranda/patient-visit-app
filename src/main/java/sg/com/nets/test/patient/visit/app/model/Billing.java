package sg.com.nets.test.patient.visit.app.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @author Miranda Aristotle Mungcal
 * 
 **/

public class Billing implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private UUID id;
	
	private int patientId;
	
	private int physicianId;
	
	private int visitId;
	
	private Date billedDateTime;

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getPhysicianId() {
		return physicianId;
	}

	public void setPhysicianId(int physicianId) {
		this.physicianId = physicianId;
	}

	public int getVisitId() {
		return visitId;
	}

	public void setVisitId(int visitId) {
		this.visitId = visitId;
	}

	public Date getBilledDateTime() {
		return billedDateTime;
	}

	public void setBilledDateTime(Date billedDateTime) {
		this.billedDateTime = billedDateTime;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	
}
