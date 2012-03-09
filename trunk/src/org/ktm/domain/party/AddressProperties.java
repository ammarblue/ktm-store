package org.ktm.domain.party;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.ktm.domain.KTMEntity;

/*
 * The AddressProperties specifics information about an Address
 * assigned to a specific Party.
 */
@Entity
@Table(name="address_properties")
public class AddressProperties extends KTMEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer uniqueId;
	private Integer version;
	private Party party;
	private Address address;
	private String useage;

    @Id
    @GeneratedValue
    @Column(name = "uniqueId", nullable = false)
	public Integer getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(Integer uniqueId) {
		this.uniqueId = uniqueId;
	}

	@Version
	@Column(name="version")
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Column(name="useage")
	public String getUseage() {
		return useage;
	}
	public void setUseage(String useage) {
		this.useage = useage;
	}
	public void setUseage(AddressEType useage) {
		this.useage = useage.getType();
	}

    @ManyToOne
	public Party getParty() {
		return party;
	}
	public void setParty(Party party) {
		this.party = party;
	}
    @ManyToOne(cascade = CascadeType.ALL)
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	@Override
	public boolean equals(Object other) {
		boolean result = false;
		if (other instanceof AddressProperties) {
			AddressProperties addp = (AddressProperties) other;
			if (addp!=null && 
				addp.getAddress() != null &&
				addp.getParty() != null &&
				this.getAddress() != null &&
				this.getParty() != null)
			{
				if (this.getUniqueId()==addp.getUniqueId() &&
					this.getAddress().equals(addp.getAddress()) &&
					this.getParty().equals(addp.getParty())) 
				{
					result = super.equals(other);
				}
			}
		}
		return result;
	}
}