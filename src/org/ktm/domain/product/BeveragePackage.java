package org.ktm.domain.product;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class BeveragePackage extends PackageType {

    private static final long serialVersionUID = -6242981125687057527L;

    private String            unitType;
    private Integer           unitCount;
    
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
