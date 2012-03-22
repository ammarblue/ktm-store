package org.ktm.domain.party;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import org.ktm.domain.KTMEntity;

/*
 * The Address represents information that can used to contact a Party.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Address extends KTMEntity implements Serializable {

    private static final long      serialVersionUID = 1L;

    private Integer                uniqueId;
    private Integer                version;
    private Set<AddressProperties> partys           = new HashSet<AddressProperties>();

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

    @OneToMany(mappedBy = "address")
    public Set<AddressProperties> getPartys() {
        return partys;
    }

    public void setPartys(Set<AddressProperties> partys) {
        this.partys = partys;
    }

}
