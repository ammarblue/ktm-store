package org.ktm.domain.money;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CreditCard extends PaymentCard {

    private static final long serialVersionUID = -4770826584148237634L;
    
    private Double creditLimit;
    private Double dailyWithdrawLimit;

    @Column
    public Double getCreditLimit() {
        return creditLimit;
    }
    
    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }

    @Column
    public Double getDailyWithdrawLimit() {
        return dailyWithdrawLimit;
    }

    public void setDailyWithdrawLimit(Double dailyWithdrawLimit) {
        this.dailyWithdrawLimit = dailyWithdrawLimit;
    }

}
