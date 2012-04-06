package org.ktm.domain.money;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Check extends PaymentMethod {

    private static final long serialVersionUID = 1L;

    private String            accountName;
    private String            accountNumber;
    private String            checkNumber;
    private String            paynee;
    private Date              dateWritten;
    private String            bankName;
    private String            bankAddress;
    private String            bankIdentificationNumber;

    @Column
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Column
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Column
    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    @Column
    public String getPaynee() {
        return paynee;
    }

    public void setPaynee(String paynee) {
        this.paynee = paynee;
    }

    @Column
    public Date getDateWritten() {
        return dateWritten;
    }

    public void setDateWritten(Date dateWritten) {
        this.dateWritten = dateWritten;
    }

    @Column
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Column
    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    @Column
    public String getBankIdentificationNumber() {
        return bankIdentificationNumber;
    }

    public void setBankIdentificationNumber(String bankIdentificationNumber) {
        this.bankIdentificationNumber = bankIdentificationNumber;
    }

}
