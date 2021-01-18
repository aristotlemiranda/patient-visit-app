package sg.com.nets.test.patient.visit.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.neo4j.ogm.annotation.NodeEntity;

import sg.com.nets.test.patient.visit.app.model.StringConstant;

/**
 * @author Miranda Aristotle Mungcal
 * 
 **/

@Entity
@NodeEntity
@Table(name=StringConstant.TBL_PHYSICIAN)
public class Physician extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(length=100)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
	

	
}
