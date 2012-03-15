package org.ktm.domain.party;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

/*
 * The PartyRoleConstraint specifies the theOfParty that is allowed to play a
 * PartyRole of a specific PartyRoleType
 */
@Entity
@Table(name = "party_role_constraint")
public class PartyRoleConstraint implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer           uniqueId;
    private Integer           version;
    private String            typeOfParty;
    private PartyRoleType     partyRoleType;

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
    @Column(name = "version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Column(name = "typeOfParty")
    public String getTypeOfParty() {
        return typeOfParty;
    }

    public void setTypeOfParty(String typeOfParty) {
        this.typeOfParty = typeOfParty;
    }

    @ManyToOne
    public PartyRoleType getPartyRoleType() {
        return partyRoleType;
    }

    public void setPartyRoleType(PartyRoleType partyRoleType) {
        this.partyRoleType = partyRoleType;
    }

    @Transient
    public boolean canPlayRole(Party party) {
        return true;
    }

}
