package sg.com.nets.test.patient.visit.app.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.neo4j.ogm.annotation.NodeEntity;

import sg.com.nets.test.patient.visit.app.model.StringConstant;

/**
 * @author Miranda Aristotle Mungcal
 * 
 **/

@Entity
@NodeEntity
@Table(name=StringConstant.TBL_HOLIDAY)
public class Holiday implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@org.springframework.data.neo4j.core.schema.Id
	@Column(length=100)
	private String name;
	
	@Temporal(TemporalType.DATE)
	private Date holidayDate;
	

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDateTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDateTime;
	
	@Column(length=100)
	private String createdBy;
	
	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Date getModifiedDateTime() {
		return modifiedDateTime;
	}

	public void setModifiedDateTime(Date modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(Date holidayDate) {
		this.holidayDate = holidayDate;
	}

	

	
}
