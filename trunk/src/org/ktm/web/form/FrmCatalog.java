package org.ktm.web.form;

public class FrmCatalog extends FrmDomain {

    private static final long serialVersionUID = -2665114871084587113L;

    private String            identifier;
    private String            name;

    // private List<FrmPackageType> packageTypes;

    public FrmCatalog() {
        super();
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

    // public List<FrmPackageType> getPackageTypes() {
    // return packageTypes;
    // }

    // public void setPackageTypes(List<FrmPackageType> packageTypes) {
    // this.packageTypes = packageTypes;
    // }

    @Override
    public int compareTo(FrmDomain o) {
        FrmCatalog other = (FrmCatalog) o;
        return name.compareTo(other.name);
    }

}
