package org.ktm.web.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FrmOrder extends FrmDomain {

    private static final long  serialVersionUID = 1L;

    private Date               createDate;
    private String             supplierId;
    private String             orderId;

    private List<FrmOrderLine> orderLines       = new ArrayList<FrmOrderLine>();

    @Override
    public int compareTo(FrmDomain arg0) {
        return 0;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<FrmOrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<FrmOrderLine> orderLines) {
        this.orderLines = orderLines;
    }

}
