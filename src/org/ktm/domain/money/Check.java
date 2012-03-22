package org.ktm.domain.money;

import java.util.Date;
import javax.persistence.Entity;

@Entity
public class Check extends Payment {

    private static final long serialVersionUID = 2794678647219779937L;

    private String accountName;
    private String accountNumber;
    private String checkNumber;
    private String paynee;
    private Date dateWritten;
    private String bankName;
    private String bankAddress;
    private String bankIdentificationNumber;
    
    public String getAccountName() {
        return accountName;
    }
    
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public String getPaynee() {
        return paynee;
    }

    public void setPaynee(String paynee) {
        this.paynee = paynee;
    }

    public Date getDateWritten() {
        return dateWritten;
    }

    public void setDateWritten(Date dateWritten) {
        this.dateWritten = dateWritten;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public String getBankIdentificationNumber() {
        return bankIdentificationNumber;
    }

    public void setBankIdentificationNumber(String bankIdentificationNumber) {
        this.bankIdentificationNumber = bankIdentificationNumber;
    }
    
}
