package org.ktm.web.form;

public class FrmInventory extends FrmDomain {

    private static final long serialVersionUID = -6498845175834308471L;

    private String            identifier;
    private String            name;

    @Override
    public int compareTo(FrmDomain other) {
        FrmInventory obj = (FrmInventory) other;
        return identifier.compareTo(obj.identifier);
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

}
