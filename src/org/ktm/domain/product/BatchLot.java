package org.ktm.domain.product;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class BatchLot extends Batch {

    private static final long serialVersionUID = 1L;

    private Integer           total;
    private Integer           balance;

    @Column
    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Column
    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
