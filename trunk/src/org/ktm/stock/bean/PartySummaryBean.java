package org.ktm.stock.bean;

import java.util.Collection;
import org.ktm.domain.KTMEntity;
import org.ktm.domain.order.PartySummary;
import org.ktm.domain.party.PartyIdentifier;
import org.ktm.web.bean.FormBean;

public class PartySummaryBean extends FormBean {

    private String partyIdentifier;
    private String name;
    private String adress;
    private String telephoneNumber;
    private String emailAddress;

    @Override
    public void loadFormCollection(Collection<?> entitys) {

    }

    @Override
    public void loadToForm(KTMEntity entity) {
        if (entity != null && entity instanceof PartySummary) {
            PartySummary paartySummary = (PartySummary) entity;
            super.loadToForm(paartySummary);
            this.setPartyIdentifier(paartySummary.getIdentifier() == null ? "" : paartySummary.getIdentifier().getIdentifier());
            this.setName(paartySummary.getName());
            this.setAdress(paartySummary.getAdress());
            this.setTelephoneNumber(paartySummary.getTelephoneNumber());
            this.setEmailAddress(paartySummary.getEmailAddress());
        }
    }

    @Override
    public void syncToEntity(KTMEntity entity) {
        if (entity != null && entity instanceof PartySummary) {
            PartySummary partySummary = (PartySummary) entity;
            super.syncToEntity(partySummary);
            partySummary.setAdress(this.getAdress());
            partySummary.setEmailAddress(this.getEmailAddress());
            partySummary.setName(this.getName());
            partySummary.setTelephoneNumber(this.getTelephoneNumber());
            PartyIdentifier identifier = partySummary.getIdentifier();
            if (identifier == null) {
                identifier = new PartyIdentifier();
                partySummary.setIdentifier(identifier);
            }
            identifier.setIdentifier(this.getPartyIdentifier());
        }
    }

    public String getPartyIdentifier() {
        return partyIdentifier;
    }

    public void setPartyIdentifier(String partyIdentifier) {
        this.partyIdentifier = partyIdentifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

}
