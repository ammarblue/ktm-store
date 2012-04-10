package org.ktm.actions.product;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
import org.ktm.actions.JsonGridFieldsAction;
import org.ktm.web.form.FrmOrder;
import org.ktm.web.form.FrmOrderLine;
import org.ktm.web.manager.OrderManager;
import org.ktm.web.manager.ServiceLocator;
import com.opensymphony.xwork2.ActionContext;

public class JsonGridOrderLine extends JsonGridFieldsAction {

    private static final long serialVersionUID = 1L;
    private Logger            log              = Logger.getLogger(JsonGridOrderLine.class);

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
        }
        return frmOrder;
    }

    @SuppressWarnings("unchecked")
    @Actions({ @Action(value = "/json-grid-order-line", results = { @Result(name = "success", type = "json") }) })
    public String execute() {
        log.debug("Page " + getPage() + " Rows " + getRows() + " Sorting Order " + getSord() + " Index Row :" + getSidx());
        log.debug("Search :" + searchField + " " + searchOper + " " + searchString);
        
        initContext();
        
        List<FrmOrderLine> myCatalogs = null;
        
        log.debug("Get Catalog List");
        try {
            myCatalogs = getOrder().getOrderLines();
            setAvailableItems(myCatalogs);
        } catch (Exception e) {
            e.printStackTrace();
        }

         

        if (sord != null && sord.equalsIgnoreCase("asc")) {
            Collections.sort(myCatalogs);
        }
        if (sord != null && sord.equalsIgnoreCase("desc")) {
            Collections.sort(myCatalogs);
            Collections.reverse(myCatalogs);
        }

        // Count all record (select count(*) from your_custumers)
        records = myCatalogs.size();

        if (totalrows != null) {
            records = totalrows;
        }

        // Calucalate until rows ware selected
        int to = (rows * page);

        // Calculate the first row to read
        int from = to - rows;

        // Set to = max rows
        if (to > records) {
            to = records;
        }

        if (loadonce) {
            if (totalrows != null && totalrows > 0) {
                setGridModel(myCatalogs.subList(0, totalrows));
            } else {
                setGridModel(myCatalogs);
            }
        } else {
            if (searchString != null && searchOper != null) {
                int id = Integer.parseInt(searchString);
                if (searchOper.equalsIgnoreCase("eq")) {
                    log.debug("search id equals " + id);
                    //List<FrmAuthen> cList = new ArrayList<FrmAuthen>();
                    // TODO: Search by id
                }else if (searchOper.equalsIgnoreCase("ne")) {
                    log.debug("search id not " + id);
                    setGridModel((List<FrmOrderLine>) getManager().findNotById(myCatalogs, id, from, to));
                } else if (searchOper.equalsIgnoreCase("lt")) {
                    log.debug("search id lesser then " + id);
                    setGridModel((List<FrmOrderLine>) getManager().findLesserAsId(myCatalogs, id, from, to));
                } else if (searchOper.equalsIgnoreCase("gt")) {
                    log.debug("search id greater then " + id);
                    setGridModel((List<FrmOrderLine>) getManager().findGreaterAsId(myCatalogs, id, from, to));
                }
            } else {
                setGridModel(myCatalogs);
            }
        }
        // Calculate total Pages
        total = (int) Math.ceil((double) records / (double) rows);
        
        return SUCCESS;
    }
    
    public void setGridModel(List<FrmOrderLine> gridModel) {
        setAvailableItems(gridModel);
    }
    
    @SuppressWarnings("unchecked")
    public List<FrmOrderLine> getGridModel() {
        return (List<FrmOrderLine>) getAvailableItems();
    }
}
