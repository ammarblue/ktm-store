package org.ktm.web.form;

import org.ktm.domain.product.ProductIdentifier;

public class FrmPackageType {

    private Integer           uniqueId;
    private Integer           version;
    private ProductIdentifier identifier;
    private String            name;
    private String            descripton;
    private Double            price;
    private Double            priceSale;
    
    public Integer getUniqueId() {
        return uniqueId;
    }
    public void setUniqueId(Integer uniqueId) {
        this.uniqueId = uniqueId;
    }
    public Integer getVersion() {
        return version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }
    public ProductIdentifier getIdentifier() {
        return identifier;
    }
    public void setIdentifier(ProductIdentifier identifier) {
        this.identifier = identifier;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescripton() {
        return descripton;
    }
    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Double getPriceSale() {
        return priceSale;
    }
    public void setPriceSale(Double priceSale) {
        this.priceSale = priceSale;
    }

}
