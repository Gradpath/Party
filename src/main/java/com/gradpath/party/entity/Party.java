package com.gradpath.party.entity;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "party")
@NoArgsConstructor
@AllArgsConstructor
public class Party {
	
	@Id
	@GeneratedValue(generator = "customPartyIdGenerator")
	@GenericGenerator(name = "customPartyIdGenerator", strategy = "com.gradpath.party.utility.CustomPartyIdGenerator")
	private String partyId;
	
	private String partyFirstName;

	private String partyMiddleName;

	private String partyLastName;

	private String email;

	private String phoneNo;

	private Date partyCreationDate;

	public Party(String partyId,String partyFirstName, String partyMiddleName, String partyLastName, String email, String phoneNo, Date partyCreationDate) {
        this.partyId = partyId;
		this.partyFirstName = partyFirstName;
        this.partyMiddleName = partyMiddleName;
        this.partyLastName = partyLastName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.partyCreationDate = partyCreationDate;
    }

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public String getPartyFirstName() {
		return partyFirstName;
	}

	public void setPartyFirstName(String partyFirstName) {
		this.partyFirstName = partyFirstName;
	}

	public String getPartyMiddleName() {
		return partyMiddleName;
	}

	public void setPartyMiddleName(String partyMiddleName) {
		this.partyMiddleName = partyMiddleName;
	}

	public String getPartyLastName() {
		return partyLastName;
	}

	public void setPartyLastName(String partyLastName) {
		this.partyLastName = partyLastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Date getPartyCreationDate() {
		return partyCreationDate;
	}

	public void setPartyCreationDate(Date partyCreationDate) {
		this.partyCreationDate = partyCreationDate;
	}
	
	

	
	

}
