package org.ktm.actions.party;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
import org.ktm.actions.JsonGridFieldsAction;
import org.ktm.web.form.FrmCustomer;
import org.ktm.web.manager.FormManager;
import org.ktm.web.manager.ServiceLocator;

public class JsonGridCustomer extends JsonGridFieldsAction {

    private static final long serialVersionUID = 8072293334749008043L;
    private Logger            log              = Logger.getLogger(JsonGridCustomer.class);
    
    @Override
    protected FormManager getManager() {
        return ServiceLocator.getCustomerManager();
    }
    
    @Actions({ @Action(value = "/json-grid-customer", results = { @Result(name = "success", type = "json") }) })
    @SuppressWarnings("unchecked")
    public String execute() {
        log.debug("Page " + getPage() + " Rows " + getRows() + " Sorting Order " + getSord() + " Index Row :" + getSidx());
        log.debug("Search :" + searchField + " " + searchOper + " " + searchString);

        initContext();
        
        log.debug("Get Customer List");
        try {
            list();
        } catch (Exception e) {

        }
        List<FrmCustomer> myCustomers = (List<FrmCustomer>) getAvailableItems();

        if (sord != null && sord.equalsIgnoreCase("asc")) {
            Collections.sort(myCustomers);
        }
        if (sord != null && sord.equalsIgnoreCase("desc")) {
            Collections.sort(myCustomers);
            Collections.reverse(myCustomers);
        }

        // Count all record (select count(*) from your_custumers)
        records = myCustomers.size();

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
                setGridModel(myCustomers.subList(0, totalrows));
            } else {
                // All Custumer
                setGridModel(myCustomers);
            }
        } else {
            // Search Custumers
            if (searchString != null && searchOper != null) {
                int id = Integer.parseInt(searchString);
                if (searchOper.equalsIgnoreCase("eq")) {
                    log.debug("search id equals " + id);
                    List<FrmCustomer> cList = new ArrayList<FrmCustomer>();
                    FrmCustomer fcustomer = (FrmCustomer) getManager().get(id);

                    if (fcustomer != null) {
                        cList.add(fcustomer);
                    }

                    setGridModel(cList);
                } else if (searchOper.equalsIgnoreCase("ne")) {
                    log.debug("search id not " + id);
                    setGridModel((List<FrmCustomer>) getManager().findNotById(myCustomers, id, from, to));
                } else if (searchOper.equalsIgnoreCase("lt")) {
                    log.debug("search id lesser then " + id);
                    setGridModel((List<FrmCustomer>) getManager().findLesserAsId(myCustomers, id, from, to));
                } else if (searchOper.equalsIgnoreCase("gt")) {
                    log.debug("search id greater then " + id);
                    setGridModel((List<FrmCustomer>) getManager().findGreaterAsId(myCustomers, id, from, to));
                }
            } else {
                setGridModel((List<FrmCustomer>) getManager().getSubList(myCustomers, from, to));
            }
        }

        // Calculate total Pages
        total = (int) Math.ceil((double) records / (double) rows);

        return SUCCESS;
    }

    public String getJSON() {
        return execute();
    }

    @SuppressWarnings("unchecked")
    public List<FrmCustomer> getGridModel() {
        return (List<FrmCustomer>) getAvailableItems();
    }

    private void setGridModel(List<FrmCustomer> subList) {
        setAvailableItems(subList);
    }
}
