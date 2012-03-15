package org.ktm.domain.party;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import org.ktm.domain.KTMEntity;
import org.ktm.domain.product.Batch;

/*
 * The PartySignature represents the identifying mark of a party. When specific
 * optional task.
 */
@Entity
@Table(name = "party_signature")
public class PartySignature extends KTMEntity {

    private static final long serialVersionUID = 1L;

    private Integer           uniqueId;
    private Integer           version;
    private Date              when;
    private String            reason;
    private PartyIdentifier   partyIdentifier;
    private Authen            authen;

    // private Batch batch;

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

    @Column(name = "signedDate")
    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }

    @Column(name = "reason")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Column(name = "partyIdentifier")
    public PartyIdentifier getPartyIdentifier() {
        return partyIdentifier;
    }

    public void setPartyIdentifier(PartyIdentifier partyIdentifier) {
        this.partyIdentifier = partyIdentifier;
    }

    @OneToOne
    public Authen getAuthen() {
        return authen;
    }

    public void setAuthen(Authen authen) {
        this.authen = authen;
    }
    /*
     * @ManyToOne public Batch getBatch() { return batch; } public void
     * setBatch(Batch batch) { this.batch = batch; }
     */

}
