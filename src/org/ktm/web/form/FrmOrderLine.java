package org.ktm.web.form;

public class FrmOrderLine extends FrmDomain {

    private static final long serialVersionUID = 1L;

    private String            productCode;
    private String            desc;
    private String            unit;
    private Double            cost;
    private Integer           quanitity;
    private Double            total;

    @Override
    public int compareTo(FrmDomain arg0) {
        return 0;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getQuanitity() {
        return quanitity;
    }

    public void setQuanitity(Integer quanitity) {
        this.quanitity = quanitity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

}
