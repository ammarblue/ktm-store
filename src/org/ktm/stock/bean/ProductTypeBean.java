package org.ktm.stock.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.ktm.domain.product.CatalogEntry;
import org.ktm.domain.product.ProductIdentifier;
import org.ktm.domain.product.ProductType;
import org.ktm.web.bean.FormBean;

public class ProductTypeBean extends FormBean {

    private String                 name;
    private String                 description;
    private String                 identifier;
    private CatalogEntryBean       catalogEntry           = new CatalogEntryBean();

    private String                 selectedCatalogEntry;

    private List<ProductTypeBean>  productTypeCollection  = new ArrayList<ProductTypeBean>(0);
    private List<CatalogEntryBean> catalogEntryCollection = new ArrayList<CatalogEntryBean>(0);

    @Override
    public void reset() {
        super.reset();
        catalogEntry.reset();
        productTypeCollection.clear();
        catalogEntryCollection.clear();
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

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public CatalogEntryBean getCatalogEntry() {
        return catalogEntry;
    }

    public void setCatalogEntry(CatalogEntryBean catalogEntry) {
        this.catalogEntry = catalogEntry;
    }

    public List<ProductTypeBean> getProductTypeCollection() {
        return productTypeCollection;
    }

    public void setProductTypeCollection(List<ProductTypeBean> productTypeCollection) {
        this.productTypeCollection = productTypeCollection;
    }

    public List<CatalogEntryBean> getCatalogEntryCollection() {
        return catalogEntryCollection;
    }

    public void setCatalogEntryCollection(List<CatalogEntryBean> catalogEntryCollection) {
        this.catalogEntryCollection = catalogEntryCollection;
    }

    public String getSelectedCatalogEntry() {
        return selectedCatalogEntry;
    }

    public void setSelectedCatalogEntry(String selectedCatalogEntry) {
        this.selectedCatalogEntry = selectedCatalogEntry;
    }

    @Override
    public void loadFormCollection(Collection<?> entitys) {
        if (entitys != null) {
            for (Object entity : entitys) {
                if (entity instanceof ProductType) {
                    ProductTypeBean bean = new ProductTypeBean();
                    bean.loadToForm((ProductType) entity);
                    productTypeCollection.add(bean);
                }
            }
        }
    }

    public void loadToForm(ProductType ptype) {
        if (ptype != null) {
            if (ptype.getIdentifier() != null) {
                this.setIdentifier(ptype.getIdentifier().getIdentifier());
            }
            this.setUniqueId(String.valueOf(ptype.getUniqueId()));
            this.setName(ptype.getName());
            this.setDescription(ptype.getDescription());
            this.getCatalogEntry().loadToForm(ptype.getCatalogEntry());
        }
    }

    public void syncToProductType(ProductType ptype) {
        if (!this.getUniqueId().isEmpty()) {
            ptype.setUniqueId(Integer.valueOf(this.getUniqueId()));
        }
        if (!this.getIdentifier().isEmpty()) {
            if (ptype.getIdentifier() == null) {
                ProductIdentifier id = new ProductIdentifier();
                id.setIdentifier(this.getIdentifier());
                ptype.setIdentifier(id);
            } else {
                ptype.getIdentifier().setIdentifier(this.getIdentifier());
            }
        }
        ptype.setName(this.getName());
        ptype.setDescription(this.getDescription());
    }

    public void loadCatalogEntryFormCollection(List<CatalogEntry> cEntrys) {
        if (cEntrys != null) {
            for (CatalogEntry cEntry : cEntrys) {
                CatalogEntryBean bean = new CatalogEntryBean();
                bean.loadToForm(cEntry);
                catalogEntryCollection.add(bean);
            }
        }
    }

}
