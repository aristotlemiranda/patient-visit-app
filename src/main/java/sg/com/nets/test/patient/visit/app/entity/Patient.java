package sg.com.nets.test.patient.visit.app.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.neo4j.ogm.annotation.NodeEntity;

import sg.com.nets.test.patient.visit.app.model.Gender;
import sg.com.nets.test.patient.visit.app.model.StringConstant;

/**
 * @author Miranda Aristotle Mungcal
 * 
 **/
@Entity
@NodeEntity
@Table(name=StringConstant.TBL_PATIENT)
public class Patient extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(length=100)
	private String name;
	
	private int age;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length=1)
	private Gender gender;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}	
	
}
