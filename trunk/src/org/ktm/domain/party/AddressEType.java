package org.ktm.domain.party;

public enum AddressEType {
	EMAIL("email"),TELEPHONE("phone"), GEOGRAPHICS("geographics"), WEBPAGE("web");
	
	private String type;

	private AddressEType(String type) {
		this.setType(type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public boolean equals(String type) {
		return this.type.equals(type);
	}
	
	public String toString() {
		return this.type;
	}
}
