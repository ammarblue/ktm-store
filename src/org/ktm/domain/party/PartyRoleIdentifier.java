package org.ktm.domain.party;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.ktm.domain.UniqueIdentifier;

/*
 * The PartyRoleIdentifier represents a unique identifier for a PartyRole.
 */
@Entity
@Table(name = "party_role_identifier")
public class PartyRoleIdentifier extends UniqueIdentifier {

    private static final long serialVersionUID = -4285085585845888905L;

    private Integer           uniqueId;
    private Integer           version;
    private String            identifier;

    @Id
    @GeneratedValue
    @Column(name = "uniqueId")
    public Integer getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(Integer uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Column(name = "version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    @Column(name = "identifier")
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

}
