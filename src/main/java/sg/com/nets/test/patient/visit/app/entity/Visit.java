package sg.com.nets.test.patient.visit.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.neo4j.ogm.annotation.NodeEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import sg.com.nets.test.patient.visit.app.model.StringConstant;

/**
 * @author Miranda Aristotle Mungcal
 * 
 **/

@Entity
@NodeEntity
@Table(name=StringConstant.TBL_VISIT)
public class Visit extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "physician_id")
	@JsonIgnoreProperties("hibernateLazyInitializer")
	private Physician physician;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id")
	@JsonIgnoreProperties("hibernateLazyInitializer")
	private Patient patient;
	
	@Column(length=400)
	private String reason;
	
	private String billingId;

	public Physician getPhysician() {
		return physician;
	}

	public void setPhysician(Physician physician) {
		this.physician = physician;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getBillingId() {
		return billingId;
	}

	public void setBillingId(String billingId) {
		this.billingId = billingId;
	}

	
}
