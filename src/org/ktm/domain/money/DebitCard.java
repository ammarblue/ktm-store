package org.ktm.domain.money;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class DebitCard extends PaymentCard {

    private static final long serialVersionUID = 1L;

    private Money             dailyWithdrawLimit;

    @Column
    public Money getDailyWithdrawLimit() {
        return dailyWithdrawLimit;
    }

    public void setDailyWithdrawLimit(Money dailyWithdrawLimit) {
        this.dailyWithdrawLimit = dailyWithdrawLimit;
    }

}
