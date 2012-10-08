package org.ktm.stock.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.ktm.domain.KTMEntity;
import org.ktm.domain.money.EPaymentType;
import org.ktm.domain.party.Supplier;

public class SupplierBean extends CollaboratorBean {

    private String             description;
    private String             payMethod;
    private String             payPolicy;
    private Integer            payDuration;
    private String             contactName;
    private String             mark;

    private List<SupplierBean> supplierCollection = new ArrayList<SupplierBean>();

    public SupplierBean() {
        super();
        setParty(new OrganizationBean());
        reset();
    }

    @Override
    public void reset() {
        super.reset();
        setPayMethod(EPaymentType.Cash.toString());
        setPayDuration(0);
    }

    @Override
    public void loadToForm(KTMEntity entity) {
        if (entity != null && entity instanceof Supplier) {
            super.loadToForm(entity);
            Supplier supplier = (Supplier) entity;
            setDescription(supplier.getDescription());
            setPayMethod(supplier.getPayMethod());
            setPayPolicy(supplier.getPayPolicy());
            setPayDuration(supplier.getPayDuration());
            setContactName(supplier.getContactName());
            setMark(supplier.getMark());
        }
    }

    @Override
    public void syncToEntity(KTMEntity entity) {
        if (entity != null && entity instanceof Supplier) {
            super.syncToEntity(entity);
            Supplier supplier = (Supplier) entity;
            supplier.setDescription(getDescription());
            supplier.setPayMethod(getPayMethod());
            supplier.setPayPolicy(getPayPolicy());
            supplier.setPayDuration(getPayDuration());
            supplier.setContactName(getContactName());
            supplier.setMark(getMark());
        }
    }

    @Override
    public void loadFormCollection(Collection<?> entitys) {
        if (entitys != null && entitys.size() > 0) {

            supplierCollection.clear();

            for (Object entity : entitys) {
                if (entity instanceof Supplier) {
                    SupplierBean bean;
                    bean = new SupplierBean();
                    bean.loadToForm((KTMEntity) entity);
                    supplierCollection.add(bean);
                }
            }
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getPayPolicy() {
        return payPolicy;
    }

    public void setPayPolicy(String payPolicy) {
        this.payPolicy = payPolicy;
    }

    public Integer getPayDuration() {
        return payDuration;
    }

    public void setPayDuration(Integer payDuration) {
        this.payDuration = payDuration;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public List<SupplierBean> getSupplierCollection() {
        return supplierCollection;
    }

    public void setSupplierCollection(List<SupplierBean> supplierCollection) {
        this.supplierCollection = supplierCollection;
    }
}
