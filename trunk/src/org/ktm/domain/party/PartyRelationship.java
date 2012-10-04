package org.ktm.domain.party;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import org.ktm.domain.KTMEntity;

/*
 * The PartyRelationship captures the fact that there is a semantic relationship
 * between two parties in which each Party plays a specific role.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class PartyRelationship extends KTMEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer           uniqueId;
    private Integer           version;
    private PartyRole         supply;
    private PartyRole         client;

    // private PartyRelationshipType type;

    @Override
    @Id
    @GeneratedValue
    @Column(name = "uniqueId", nullable = false)
    public Integer getUniqueId() {
        return uniqueId;
    }

    @Override
    public void setUniqueId(Integer uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Override
    @Version
    @Column(name = "version")
    public Integer getVersion() {
        return version;
    }

    @Override
    public void setVersion(Integer version) {
        this.version = version;
    }

    @ManyToOne
    public PartyRole getSupply() {
        return supply;
    }

    public void setSupply(PartyRole supply) {
        this.supply = supply;
    }

    @ManyToOne
    public PartyRole getClient() {
        return client;
    }

    public void setClient(PartyRole client) {
        this.client = client;
    }

    // @ManyToOne
    // public PartyRelationshipType getType() {
    // return type;
    // }

    // public void setType(PartyRelationshipType type) {
    // this.type = type;
    // }

}
