package org.ktm.stock.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.ktm.domain.KTMEntity;
import org.ktm.domain.product.CatalogEntryType;
import org.ktm.domain.product.ProductIdentifier;
import org.ktm.domain.product.ProductType;
import org.ktm.web.bean.FormBean;

public class ProductTypeBean extends FormBean {

    private String                     name;
    private String                     description;
    private String                     identifier;
    private String                     catalogEntryTypeName;

    private String                     selectedCatalogEntryType;

    private List<ProductTypeBean>      productTypeCollection      = new ArrayList<ProductTypeBean>(0);
    private List<CatalogEntryTypeBean> catalogEntryTypeCollection = new ArrayList<CatalogEntryTypeBean>(0);

    @Override
    public void reset() {
        super.reset();
        productTypeCollection.clear();
        catalogEntryTypeCollection.clear();
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

    public String getCatalogEntryTypeName() {
        return catalogEntryTypeName;
    }

    public void setCatalogEntryTypeName(String catalogEntryTypeName) {
        this.catalogEntryTypeName = catalogEntryTypeName;
    }

    public List<ProductTypeBean> getProductTypeCollection() {
        return productTypeCollection;
    }

    public void setProductTypeCollection(List<ProductTypeBean> productTypeCollection) {
        this.productTypeCollection = productTypeCollection;
    }

    public List<CatalogEntryTypeBean> getCatalogEntryTypeCollection() {
        return catalogEntryTypeCollection;
    }

    public void setCatalogEntryTypeCollection(List<CatalogEntryTypeBean> catalogEntryTypeCollection) {
        this.catalogEntryTypeCollection = catalogEntryTypeCollection;
    }

    public String getSelectedCatalogEntryType() {
        return selectedCatalogEntryType;
    }

    public void setSelectedCatalogEntryType(String selectedCatalogEntryType) {
        this.selectedCatalogEntryType = selectedCatalogEntryType;
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
        }
    }

    @Override
    public void syncToEntity(KTMEntity entity) {
        if (entity != null && entity instanceof ProductType) {
            ProductType productType = (ProductType) entity;
            if (!this.getUniqueId().isEmpty()) {
                productType.setUniqueId(Integer.valueOf(this.getUniqueId()));
            }
            if (!this.getIdentifier().isEmpty()) {
                if (productType.getIdentifier() == null) {
                    ProductIdentifier id = new ProductIdentifier();
                    id.setIdentifier(this.getIdentifier());
                    productType.setIdentifier(id);
                } else {
                    productType.getIdentifier().setIdentifier(this.getIdentifier());
                }
            }
            productType.setName(this.getName());
            productType.setDescription(this.getDescription());
        }
    }

    public void loadCatalogEntryTypeFormCollection(List<CatalogEntryType> cEntrys) {
        if (cEntrys != null) {
            for (CatalogEntryType cEntry : cEntrys) {
                CatalogEntryTypeBean bean = new CatalogEntryTypeBean();
                bean.loadToForm(cEntry);
                catalogEntryTypeCollection.add(bean);
            }
        }
    }

}
