package org.ktm.stock.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.ktm.domain.KTMEntity;
import org.ktm.domain.party.PartyRoleType;
import org.ktm.web.bean.FormBean;

public class PartyRoleTypeBean extends FormBean {

    private String                  name;
    private String                  description;

    private List<PartyRoleTypeBean> partyRoleTypeCollection = new ArrayList<PartyRoleTypeBean>(0);

    @Override
    public void reset() {
        super.reset();
        this.setName("");
        this.setDescription("");
    }

    @Override
    public void loadToForm(KTMEntity entity) {
        if (entity != null && entity instanceof PartyRoleType) {
            super.loadToForm(entity);
            PartyRoleType partyRoleType = (PartyRoleType) entity;
            this.setName(partyRoleType.getName());
            this.setDescription(partyRoleType.getDescription());
        }
    }

    @Override
    public void loadFormCollection(Collection<?> entitys) {
        if (entitys != null) {
            partyRoleTypeCollection.clear();
            for (Object entity : entitys) {
                if (entity instanceof PartyRoleType) {
                    PartyRoleTypeBean bean = new PartyRoleTypeBean();
                    bean.loadToForm((PartyRoleType) entity);
                    partyRoleTypeCollection.add(bean);
                }
            }
        }
    }

    @Override
    public void syncToEntity(KTMEntity entity) {
        if (entity != null && entity instanceof PartyRoleType) {
            super.syncToEntity(entity);

            PartyRoleType partyRoleType = (PartyRoleType) entity;
            partyRoleType.setName(this.getName());
            partyRoleType.setDescription(this.getDescription());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PartyRoleTypeBean> getPartyRoleTypeCollection() {
        return partyRoleTypeCollection;
    }

    public void setPartyRoleTypeCollection(List<PartyRoleTypeBean> partyRoleTypeCollection) {
        this.partyRoleTypeCollection = partyRoleTypeCollection;
    }

}
