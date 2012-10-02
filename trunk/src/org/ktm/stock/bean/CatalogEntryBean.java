package org.ktm.stock.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.ktm.domain.product.CatalogEntry;

public class CatalogEntryBean extends FormBean {

    private String                 identifier;
    private String                 description;

    private List<CatalogEntryBean> catalogEntryCollection = new ArrayList<CatalogEntryBean>(0);

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CatalogEntryBean> getCatalogEntryCollection() {
        return catalogEntryCollection;
    }

    public void setCatalogEntryCollection(List<CatalogEntryBean> catalogEntryCollection) {
        this.catalogEntryCollection = catalogEntryCollection;
    }

    public void syncToCatalogEntry(CatalogEntry catalogEntry) {
        if (catalogEntry != null) {
            if (!this.getUniqueId().isEmpty()) {
                catalogEntry.setUniqueId(Integer.valueOf(this.getUniqueId()));
            }
            catalogEntry.setIdentifier(this.getIdentifier());
            catalogEntry.setDescription(this.getDescription());
        }
    }

    public void loadToForm(CatalogEntry catalogEntry) {
        if (catalogEntry != null) {
            this.setUniqueId(String.valueOf(catalogEntry.getUniqueId()));
            this.setIdentifier(catalogEntry.getIdentifier());
            this.setDescription(catalogEntry.getDescription());
        }
    }

    public void loadFormCollection(List<CatalogEntry> catalogEntrys) {
        if (catalogEntrys != null) {
            catalogEntryCollection.clear();
            Iterator<CatalogEntry> it = catalogEntrys.iterator();
            while (it.hasNext()) {
                CatalogEntryBean bean = new CatalogEntryBean();
                bean.loadToForm(it.next());
                catalogEntryCollection.add(bean);
            }
        }
    }

}
