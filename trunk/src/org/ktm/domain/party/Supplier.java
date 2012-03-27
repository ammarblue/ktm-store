package org.ktm.domain.party;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Supplier extends Organization {

    private static final long serialVersionUID = 3772334556674798074L;

    private String description;
    private Integer payMethod;
    private Integer payDuration;
    private String contactName;
    private String mark;

    @Column(name = "description")
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "payMethod")
    public Integer getPayMethod() {
        return payMethod;
    }
    
    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    @Column(name = "payDuration")
    public Integer getPayDuration() {
        return payDuration;
    }
    
    public void setPayDuration(Integer payDuration) {
        this.payDuration = payDuration;
    }

    @Column(name = "contactName")
    public String getContactName() {
        return contactName;
    }
    
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @Column(name = "mark")
    public String getMark() {
        return mark;
    }
    
    public void setMark(String mark) {
        this.mark = mark;
    }
    
}
