package org.ktm.domain.money;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class DebitCard extends PaymentCard {

    private static final long serialVersionUID = -9184047790369210610L;

    private Money dailyWithdrawLimit;

    @Column
    public Money getDailyWithdrawLimit() {
        return dailyWithdrawLimit;
    }

    public void setDailyWithdrawLimit(Money dailyWithdrawLimit) {
        this.dailyWithdrawLimit = dailyWithdrawLimit;
    }
    
}
