package org.ktm.web.form;

public class FrmProductType extends FrmDomain {

    private static final long serialVersionUID = 4339114129576929550L;

    private String            name;
    private String            descripton;
    private String            identifier;
    private double            price1;
    private double            price2;

    @Override
    public int compareTo(FrmDomain o) {
        FrmProductType other = (FrmProductType) o;
        return identifier.compareTo(other.identifier);
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

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public double getPrice1() {
        return price1;
    }

    public void setPrice1(double price1) {
        this.price1 = price1;
    }

    public double getPrice2() {
        return price2;
    }

    public void setPrice2(double price2) {
        this.price2 = price2;
    }

}
