package org.ktm.web.form;

import java.util.List;
import org.ktm.domain.KTMEntity;

public class FrmProduct extends KTMEntity {

    private static final long    serialVersionUID = -2665114871084587113L;

    private Integer              uniqueId;
    private Integer              version;
    private String               catalogIdentifier;
    private String               descripton;
    private List<FrmPackageType> packageTypes;

    @Override
    public Integer getUniqueId() {
        return uniqueId;
    }

    @Override
    public void setUniqueId(Integer uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Override
    public Integer getVersion() {
        return version;
    }

    @Override
    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getCatalogIdentifier() {
        return catalogIdentifier;
    }

    public void setCatalogIdentifier(String catalogIdentifier) {
        this.catalogIdentifier = catalogIdentifier;
    }

    public String getDescripton() {
        return descripton;
    }

    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }

    public List<FrmPackageType> getPackageTypes() {
        return packageTypes;
    }

    public void setPackageTypes(List<FrmPackageType> packageTypes) {
        this.packageTypes = packageTypes;
    }

}
