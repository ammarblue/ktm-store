package org.ktm.web.form;

public class FrmBeveragePackage extends FrmPackageType {

    private static final long serialVersionUID = -4957684870299599276L;

    private String            unitType;
    private Integer           unitCount;
    
    // for groupping
    private String            catalogName;
    
    public String getUnitType() {
        return unitType;
    }
    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }
    public Integer getUnitCount() {
        return unitCount;
    }
    public void setUnitCount(Integer unitCount) {
        this.unitCount = unitCount;
    }
    public String getCatalogName() {
        return catalogName;
    }
    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

}
