package org.ktm.stock.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.ktm.domain.KTMEntity;
import org.ktm.domain.party.PartyRole;
import org.ktm.domain.party.PartyRoleIdentifier;

public class PartyRoleBean extends FormBean {

    private String              name;
    private PartyBean           party;
    private String              identifier;

    private List<PartyRoleBean> partyRoleCollection = new ArrayList<PartyRoleBean>(0);

    @Override
    public void reset() {
        super.reset();
        party.reset();

        partyRoleCollection.clear();
    }

    @Override
    public void loadToForm(KTMEntity entity) {
        if (entity != null && entity instanceof PartyRole) {
            super.loadToForm(entity);
            PartyRole partyRole = (PartyRole) entity;
            this.setIdentifier(partyRole.getIdentifier().getIdentifier());
            party.loadToForm(partyRole.getParty());
        }
    }

    @Override
    public void syncToEntity(KTMEntity entity) {
        if (entity != null && entity instanceof PartyRole) {
            super.syncToEntity(entity);

            PartyRole partyRole = (PartyRole) entity;
            party.syncToEntity(partyRole.getParty());
            partyRole.setName(this.getName());
            PartyRoleIdentifier identifier = partyRole.getIdentifier();
            if (identifier == null) {
                identifier = new PartyRoleIdentifier();
                partyRole.setIdentifier(identifier);
            }
            identifier.setIdentifier(this.getIdentifier());
        }
    }

    @Override
    public void loadFormCollection(Collection<?> entitys) {
        if (entitys != null) {
            for (Object entity : entitys) {
                if (entity instanceof PartyRole) {
                    PartyRole partyRole = (PartyRole) entity;
                    PartyRoleBean bean = new PartyRoleBean();
                    bean.loadToForm(partyRole);
                    partyRoleCollection.add(bean);
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PartyBean getParty() {
        return party;
    }

    public void setParty(PartyBean party) {
        this.party = party;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public List<PartyRoleBean> getPartyRoleCollection() {
        return partyRoleCollection;
    }

    public void setPartyRoleCollection(List<PartyRoleBean> partyRoleCollection) {
        this.partyRoleCollection = partyRoleCollection;
    }

}
