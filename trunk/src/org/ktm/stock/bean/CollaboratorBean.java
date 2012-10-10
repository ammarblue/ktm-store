package org.ktm.stock.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.ktm.domain.KTMEntity;
import org.ktm.domain.money.EPaymentType;
import org.ktm.domain.party.Collaborator;

public class CollaboratorBean extends PartyRoleBean {

    private String                 description;
    private String                 payMethod;
    private String                 payPolicy;
    private Integer                payDuration;
    private String                 contactName;
    private String                 mark;

    private List<CollaboratorBean> collaboratorCollection = new ArrayList<CollaboratorBean>();

    public CollaboratorBean() {
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
        if (entity != null && entity instanceof Collaborator) {
            super.loadToForm(entity);
            Collaborator collaborator = (Collaborator) entity;
            setDescription(collaborator.getDescription());
            setPayMethod(collaborator.getPayMethod());
            setPayPolicy(collaborator.getPayPolicy());
            setPayDuration(collaborator.getPayDuration());
            setContactName(collaborator.getContactName());
            setMark(collaborator.getMark());
        }
    }

    @Override
    public void syncToEntity(KTMEntity entity) {
        if (entity != null && entity instanceof Collaborator) {
            super.syncToEntity(entity);
            Collaborator collaborator = (Collaborator) entity;
            collaborator.setDescription(getDescription());
            collaborator.setPayMethod(getPayMethod());
            collaborator.setPayPolicy(getPayPolicy());
            collaborator.setPayDuration(getPayDuration());
            collaborator.setContactName(getContactName());
            collaborator.setMark(getMark());
        }
    }

    @Override
    public void loadFormCollection(Collection<?> entitys) {
        if (entitys != null && entitys.size() > 0) {

            collaboratorCollection.clear();

            for (Object entity : entitys) {
                if (entity instanceof Collaborator) {
                    CollaboratorBean bean;
                    bean = new CollaboratorBean();
                    bean.loadToForm((KTMEntity) entity);
                    collaboratorCollection.add(bean);
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

    public List<CollaboratorBean> getCollaboratorCollection() {
        return collaboratorCollection;
    }

    public void setCollaboratorCollection(List<CollaboratorBean> collaboratorCollection) {
        this.collaboratorCollection = collaboratorCollection;
    }
}
