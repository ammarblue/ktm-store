package org.ktm.web.form;

import org.ktm.domain.KTMEntity;

public class FrmParty extends KTMEntity implements Comparable<FrmParty> {

	private static final long serialVersionUID = 1L;
	
	private String identifier;
	private String registeredIdentifier;
	// address
	private String emailAddress;
	private String tel;
	// authen
	private String username;
	private String password;

	public FrmParty() {
		setUniqueId(0);
		setIdentifier("");
	}

	private Integer uniqueId;
	
	public Integer getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(Integer uniqueId) {
		this.uniqueId = uniqueId;
	}
	
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Integer getVersion() {
		return null;
	}

	public void setVersion(Integer version) {
		
	}

	public String getRegisteredIdentifier() {
		return registeredIdentifier;
	}

	public void setRegisteredIdentifier(String registeredIdentifier) {
		this.registeredIdentifier = registeredIdentifier;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int compareTo(FrmParty o) {
		return this.getUniqueId().compareTo(o.getUniqueId());
	}
	
}
