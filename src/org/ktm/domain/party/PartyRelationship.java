package org.ktm.domain.party;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

/*
 * The PartyRelationship captures the fact that there is a semantic relationship
 * between two parties in which each Party plays a specific role.
 */
@Entity
public class PartyRelationship implements Serializable {

    private static final long     serialVersionUID = 1L;

    private Integer               uniqueId;
    private Integer               version;
    private PartyRole             supply;
    private PartyRole             client;
    private PartyRelationshipType type;

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

    @ManyToOne
    public PartyRelationshipType getType() {
        return type;
    }

    public void setType(PartyRelationshipType type) {
        this.type = type;
    }

}
