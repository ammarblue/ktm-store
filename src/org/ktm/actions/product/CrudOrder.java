package org.ktm.actions.product;

import java.text.ParseException;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.ktm.actions.CrudAction;
import org.ktm.dao.KTMEMDaoFactory;
import org.ktm.dao.KTMMaxIdentifierDao;
import org.ktm.exception.UpdateException;
import org.ktm.web.form.FrmOrder;
import org.ktm.web.manager.OrderManager;
import org.ktm.web.manager.ServiceLocator;
import org.ktm.web.utils.DateUtils;
import com.opensymphony.xwork2.ActionContext;

public class CrudOrder extends CrudAction {

    private static final long serialVersionUID = 1L;
    private Logger            log              = Logger.getLogger(CrudOrder.class);

    private String orderDate;
    private String supplierId;
    private String orderNumber;
    
    @Override
    protected OrderManager getManager() {
        return (OrderManager) ServiceLocator.getOrderManager();
    }
    
    protected FrmOrder getOrder() {
        Map<String, Object> session = ActionContext.getContext().getSession();
        FrmOrder frmOrder = (FrmOrder) session.get("current_order");
        if (frmOrder == null) {
            frmOrder = new FrmOrder();
            frmOrder.setNew(true);
            session.put("current_order", frmOrder);
            
            KTMMaxIdentifierDao dao = KTMEMDaoFactory.getInstance().getKTMMaxIdentifierDao();
            try {
                String newId = dao.getMaxPurchaseOrderId();
                frmOrder.setOrderId(newId);
            } catch (UpdateException e) {
                e.printStackTrace();
            }
        }

        //session.put("current_order", null);
        
        return frmOrder;
    }
    
    protected void mergeOrder(FrmOrder form) {
        try {
            if (orderDate==null || (orderDate!=null && orderDate.isEmpty())) {
                orderDate = DateUtils.formatDate(form.getCreateDate());
            } else {
                form.setCreateDate(DateUtils.formatString(orderDate));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        if (supplierId==null || (supplierId!=null && supplierId.isEmpty())) {
            supplierId = form.getSupplierId();
        } else {
            form.setSupplierId(supplierId);
        }
        if (orderNumber==null || (orderNumber!=null && orderNumber.isEmpty())) {
            orderNumber = form.getOrderId();
        } else {
            form.setOrderId(orderNumber);
        }
    }
    
    @Action(value = "/crud-order", results = {
      @Result(name = SUCCESS, location = "transaction-receive-from-supplier", type = "tiles"),
      @Result(location = "simpleecho.jsp", name = "input")
    })
    public String execute() throws Exception {
        log.info("orderDate: " + orderDate);
        log.info("supplierId: " + supplierId);
        log.info("orderNumber: " + orderNumber);

        initContext();
        
        FrmOrder frmOrder = getOrder();
        mergeOrder(frmOrder);
        
        return SUCCESS;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
