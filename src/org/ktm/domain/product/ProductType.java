package org.ktm.domain.product;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import org.ktm.domain.KTMEntity;
import org.ktm.domain.money.Price;

@Entity
@Table(name = "product_type")
public class ProductType extends KTMEntity {

    private static final long serialVersionUID = 6188931282436592858L;

    private Integer           uniqueId;
    private Integer           version;
    private String            name;
    private String            descripton;
    private ProductIdentifier identifier;
    private Set<Price>        prices;

    // option properties
    private String            unitType;
    private Integer           unitCount;

    @Id
    @GeneratedValue
    @Column(name = "uniqueId", nullable = false)
    public Integer getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(Integer uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Version
    @Column(name = "version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescripton() {
        return descripton;
    }

    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public ProductIdentifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(ProductIdentifier identifier) {
        this.identifier = identifier;
    }

    @OneToMany(mappedBy = "productType", cascade = CascadeType.ALL)
    public Set<Price> getPrices() {
        return prices;
    }

    public void setPrices(Set<Price> prices) {
        this.prices = prices;
    }

    @Column(name = "unit_type")
    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    @Column(name = "unit_count")
    public Integer getUnitCount() {
        return unitCount;
    }

    public void setUnitCount(Integer unitCount) {
        this.unitCount = unitCount;
    }

}
