package org.ktm.stock.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.ktm.domain.product.CatalogEntryType;
import org.ktm.web.bean.FormBean;

public class CatalogEntryTypeBean extends FormBean {

    private String                     identifier;
    private String                     name;

    private List<CatalogEntryTypeBean> catalogEntryTypeCollection = new ArrayList<CatalogEntryTypeBean>(0);

    @Override
    public void reset() {
        super.reset();
        catalogEntryTypeCollection.clear();
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CatalogEntryTypeBean> getCatalogEntryTypeCollection() {
        return catalogEntryTypeCollection;
    }

    public void setCatalogEntryTypeCollection(List<CatalogEntryTypeBean> catalogEntryTypeCollection) {
        this.catalogEntryTypeCollection = catalogEntryTypeCollection;
    }

    public void syncToCatalogEntryType(CatalogEntryType catalogEntry) {
        if (catalogEntry != null) {
            if (!this.getUniqueId().isEmpty()) {
                catalogEntry.setUniqueId(Integer.valueOf(this.getUniqueId()));
            }
            catalogEntry.setIdentifier(this.getIdentifier());
            catalogEntry.setName(this.getName());

        }
    }

    public void loadToForm(CatalogEntryType catalogEntryType) {
        if (catalogEntryType != null) {
            this.setUniqueId(String.valueOf(catalogEntryType.getUniqueId()));
            this.setIdentifier(catalogEntryType.getIdentifier());
            this.setName(catalogEntryType.getName());
        }
    }

    @Override
    public void loadFormCollection(Collection<?> entitys) {
        if (entitys != null) {
            for (Object entity : entitys) {
                if (entity instanceof CatalogEntryType) {
                    CatalogEntryTypeBean bean = new CatalogEntryTypeBean();
                    bean.loadToForm((CatalogEntryType) entity);
                    catalogEntryTypeCollection.add(bean);
                }
            }
        }
    }

}
