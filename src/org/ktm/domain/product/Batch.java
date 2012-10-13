package org.ktm.domain.product;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import org.ktm.domain.KTMEntity;
import org.ktm.domain.party.PartySignature;

@Entity
@Table(name = "batch")
public class Batch implements KTMEntity {

    private static final long   serialVersionUID = 1L;

    private Integer             uniqueId;
    private Integer             version;
    private String              batchIdentifier;
    private ProductIdentifier   batchOf;
    private Date                dateProduced;
    private Date                sellBy;
    private Date                bestBefore;
    private String              startSerialNumber;
    private String              endSerialNumber;
    private String              comment;
    private Set<PartySignature> checkedBy        = new HashSet<PartySignature>();

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

    @Column(name = "batchIdentifier")
    public String getBatchIdentifier() {
        return batchIdentifier;
    }

    public void setBatchIdentifier(String batchIdentifier) {
        this.batchIdentifier = batchIdentifier;
    }

    @Column(name = "batchOf")
    public ProductIdentifier getBatchOf() {
        return batchOf;
    }

    public void setBatchOf(ProductIdentifier batchOf) {
        this.batchOf = batchOf;
    }

    @Column(name = "dateProduced")
    public Date getDateProduced() {
        return dateProduced;
    }

    public void setDateProduced(Date dateProduced) {
        this.dateProduced = dateProduced;
    }

    @Column(name = "sellBy")
    public Date getSellBy() {
        return sellBy;
    }

    public void setSellBy(Date sellBy) {
        this.sellBy = sellBy;
    }

    @Column(name = "bestBefore")
    public Date getBestBefore() {
        return bestBefore;
    }

    public void setBestBefore(Date bestBefore) {
        this.bestBefore = bestBefore;
    }

    @Column(name = "startSerialNumber")
    public String getStartSerialNumber() {
        return startSerialNumber;
    }

    public void setStartSerialNumber(String startSerialNumber) {
        this.startSerialNumber = startSerialNumber;
    }

    @Column(name = "endSerialNumber")
    public String getEndSerialNumber() {
        return endSerialNumber;
    }

    public void setEndSerialNumber(String endSerialNumber) {
        this.endSerialNumber = endSerialNumber;
    }

    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @OneToMany
    public Set<PartySignature> getCheckedBy() {
        return checkedBy;
    }

    public void setCheckedBy(Set<PartySignature> checkedBy) {
        this.checkedBy = checkedBy;
    }

}
