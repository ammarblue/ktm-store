package org.ktm.domain.money;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class PaymentCard extends PaymentMethod {

    private static final long serialVersionUID = 1L;

    private String            cardAssociationName;
    private String            cardNumber;
    private String            nameOnCard;
    private Date              expiryDate;
    private String            billingAddress;
    private Date              validFrom;
    private String            cardVerificationCode;
    private String            issueNumber;

    @Column
    public String getCardAssociationName() {
        return cardAssociationName;
    }

    public void setCardAssociationName(String cardAssociationName) {
        this.cardAssociationName = cardAssociationName;
    }

    @Column
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Column
    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    @Column
    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Column
    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    @Column
    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    @Column
    public String getCardVerificationCode() {
        return cardVerificationCode;
    }

    public void setCardVerificationCode(String cardVerificationCode) {
        this.cardVerificationCode = cardVerificationCode;
    }

    @Column
    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }

}
