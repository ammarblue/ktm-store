package org.ktm.domain.money;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Payment extends Money implements Serializable {

    private static final long serialVersionUID = 9151493751652052575L;

    private Date              dateMade;
    private Date              dateReceived;
    private Date              dateDue;
    private Date              dateCleared;

    @Override
    @Id
    @GeneratedValue
    @Column(name = "uniqueId", nullable = false)
    public Integer getUniqueId() {
        return super.getUniqueId();
    }

    public void setUniqueId(Integer uniqueId) {
        super.setUniqueId(uniqueId);
    }

    @Override
    @Version
    public Integer getVersion() {
        return super.getVersion();
    }

    public void setVersion(Integer version) {
        super.setVersion(version);
    }

    public Date getDateMade() {
        return dateMade;
    }

    public void setDateMade(Date dateMade) {
        this.dateMade = dateMade;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    public Date getDateDue() {
        return dateDue;
    }

    public void setDateDue(Date dateDue) {
        this.dateDue = dateDue;
    }

    public Date getDateCleared() {
        return dateCleared;
    }

    public void setDateCleared(Date dateCleared) {
        this.dateCleared = dateCleared;
    }

    @Override
    public Double getAmount() {
        return super.getAmount();
    }

}
