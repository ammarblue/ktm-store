package org.ktm.actions.product;

import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
import org.ktm.actions.CrudAction;
import org.ktm.web.form.FrmOrder;
import org.ktm.web.form.FrmOrderLine;
import org.ktm.web.manager.FormManager;
import org.ktm.web.manager.ServiceLocator;
import com.opensymphony.xwork2.ActionContext;

public class CurdGridOrderLine extends CrudAction {

    private static final long serialVersionUID = 1L;
    private Logger            log              = Logger.getLogger(CurdGridOrderLine.class);

    private String            oper             = "";
    private String            id;
    private String            productCode;
    private String            desc;
    private String            unit;
    private Double            cost;
    private Integer           quanitity;
    private Double            total;
    
    @Override
    protected FormManager getManager() {
        return ServiceLocator.getOrderManager();
    }
    
    protected FrmOrder getOrder() {
        Map<String, Object> session = ActionContext.getContext().getSession();
        FrmOrder frmOrder = (FrmOrder) session.get("current_order");
        if (frmOrder == null) {
            frmOrder = new FrmOrder();
            frmOrder.setNew(true);
            session.put("current_order", frmOrder);
        }
        return frmOrder;
    }
    
    protected void parameterToForm(FrmOrderLine orderLine) {
        orderLine.setProductCode(productCode);
        orderLine.setDesc(desc);
        orderLine.setUnit(unit);
        orderLine.setCost(cost);
        orderLine.setQuanitity(quanitity);
        orderLine.setTotal(total);
    }
    
    protected FrmOrderLine getOrderLine(Integer id) {
        for (FrmOrderLine ol : getOrder().getOrderLines()) {
            if (ol.getId() == id) {
                return ol;
            }
        }
        return null;
    }
    
    @Actions({ @Action(value = "/crud-grid-order-line", results = { @Result(location = "simpleecho.jsp", name = "success"), @Result(location = "simpleecho.jsp", name = "input") }) })
    public String execute() {
        log.debug("oper :" + oper);
        log.debug("id :" + id);
        log.debug("productCode :" + productCode);
        log.debug("desc :" + desc);
        log.debug("unit :" + unit);
        log.debug("cost :" + cost);
        log.debug("quanitity :" + quanitity);
        log.debug("total :" + total);

        initContext();

        if (oper.equalsIgnoreCase("del")) {

        } else {
            FrmOrderLine orderLine = null;
            
            if (oper.equalsIgnoreCase("add")) {
                log.debug("Add new OrderLine");
                orderLine = new FrmOrderLine();
                parameterToForm(orderLine);
                orderLine.setNew(true);
                getOrder().getOrderLines().add(orderLine);
            } else if (oper.equalsIgnoreCase("edit")) {
                log.debug("Edit OrderLine");
                orderLine = getOrderLine(Integer.parseInt(id));
                if (orderLine != null) {
                    orderLine.setId(Integer.parseInt(id));
                    parameterToForm(orderLine);
                    orderLine.setNew(false);
                }
            }
        }
        return SUCCESS;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
