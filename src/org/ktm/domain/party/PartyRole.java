package org.ktm.domain.party;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import org.ktm.domain.KTMEntity;

/*
 * The PartyRole captures the semantic of the role played by a Party in a
 * particular PartyRelationship
 */
@Entity
@Table(name = "party_role", uniqueConstraints = { @UniqueConstraint(columnNames = { "uniqueId", "name" }) })
public class PartyRole extends KTMEntity {

    private static final long   serialVersionUID = 1L;

    private Integer             uniqueId;
    private Integer             version;
    private String              name;
    private Party               party;
    private PartyRoleType       type;
    private PartyRoleIdentifier identifier;

    // private Set<Preperence> preperences = new HashSet<Preperence>(0);

    public PartyRole() {
        super();
    }

    public PartyRole(String role) {
        this.name = role;
    }

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

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    @ManyToOne
    public PartyRoleType getType() {
        return type;
    }

    public void setType(PartyRoleType type) {
        this.type = type;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public PartyRoleIdentifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(PartyRoleIdentifier identifier) {
        this.identifier = identifier;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof PartyRole) {
            PartyRole other = (PartyRole) obj;
            return this.getName().equals(other.getName());
        }
        return false;
    }
}
