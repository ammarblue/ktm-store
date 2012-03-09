package org.ktm.domain.party;

import java.text.ParseException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.ktm.web.utils.DateUtils;

/*
 * The Person represents information about a human being.
 */
@Entity
@Table(name = "person")
public class Person extends Party {

	private static final long serialVersionUID = 1L;

	private String preName;
	private String firstName;
	private String lastName;
	private Date birthDay;
	private ISOGender gender;
	
	@Column(name="preName")
	public String getPreName() {
		return preName;
	}

	public void setPreName(String preName) {
		this.preName = preName;
	}

	@Column(name="firstName")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name="lastName")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name="birthDay")
	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public void setBirthDay(String birthDay) {
		try {
			setBirthDay(DateUtils.formatString(birthDay));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Column(name="iso_gender")
	public ISOGender getGender() {
		return gender;
	}

	public void setGender(ISOGender gender) {
		this.gender = gender;
	}

    /*
     * This function is used for the Autocompleter example with seperate label element.
     */
	@Transient
    public String getLabel() {
	return this.preName + this.firstName + " " + this.lastName;
    }
	
}
