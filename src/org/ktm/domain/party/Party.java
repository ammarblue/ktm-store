package org.ktm.domain.party;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.ktm.domain.KTMEntity;

/*
 * The Party represents an identifiable, addressable unit that may have a
 * legal status and that normally has autonomous control over (at least some of) its
 * actions.
 */
@Entity
@Table(name = "party")
@Inheritance(strategy=InheritanceType.JOINED)
public class Party extends KTMEntity implements Comparable<Party> {

	private static final long serialVersionUID = 1L;
	
	private Integer uniqueId;
	private Integer version;
	private PartyIdentifier identifier;
	private Set<RegisteredIdentifier> registeredIdentifiers = new HashSet<RegisteredIdentifier>(0);
	private Set<AddressProperties> addresses = new HashSet<AddressProperties>();
	private Set<PartyRole> roles = new HashSet<PartyRole>(0);
	//private Set<Preperence> preperences = new HashSet<Preperence>(0);

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

	public int compareTo(Party party) {
		return uniqueId.compareTo(party.uniqueId);
	}
	
	@Transient
	public String getLabel() {
	    return "party";
	}
	
	@OneToOne(cascade = CascadeType.ALL)
	public PartyIdentifier getIdentifier() {
		return identifier;
	}

	public void setIdentifier(PartyIdentifier identifier) {
		this.identifier = identifier;
	}

	@OneToMany (mappedBy="party", cascade = CascadeType.ALL)
	public Set<RegisteredIdentifier> getRegisteredIdentifiers() {
		return registeredIdentifiers;
	}

	public void setRegisteredIdentifiers(Set<RegisteredIdentifier> registeredIdentifiers) {
		this.registeredIdentifiers = registeredIdentifiers;
	}

	@OneToMany (mappedBy="party", cascade = CascadeType.ALL)
	public Set<AddressProperties> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<AddressProperties> addresses) {
		this.addresses = addresses;
	}

	@OneToMany(mappedBy="party", cascade = CascadeType.ALL)
	public Set<PartyRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<PartyRole> roles) {
		this.roles = roles;
	}
	
	@Override
	public boolean equals(Object other) {
		boolean result = false;
		if (other instanceof Party) {
			Party party = (Party) other;
			if (party!=null && 
				party.getIdentifier() != null &&
				this.getIdentifier() != null)
			{
				if (this.getIdentifier().equals(party.getIdentifier())) {
					result = super.equals(other);
				}
			}
		}
		return result;
	}
	
}
