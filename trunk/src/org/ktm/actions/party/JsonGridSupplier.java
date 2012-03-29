package org.ktm.actions.party;

import java.util.Collections;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
import org.ktm.actions.JsonGridFieldsAction;
import org.ktm.web.form.FrmSupplier;
import org.ktm.web.manager.ServiceLocator;
import org.ktm.web.manager.SupplierManager;

public class JsonGridSupplier extends JsonGridFieldsAction {

    private static final long serialVersionUID = 1L;
    private Logger            log              = Logger.getLogger(JsonGridSupplier.class);

    @Override
    protected SupplierManager getManager() {
        return ServiceLocator.getSupplierManager();
    }

    @SuppressWarnings("unchecked")
    @Actions({ @Action(value = "/json-grid-supplier", results = { @Result(name = "success", type = "json"), @Result(name = INPUT, location = "database-product", type = "tiles") }) })
    public String execute() {
        log.debug("Page " + getPage() + " Rows " + getRows() + " Sorting Order " + getSord() + " Index Row :" + getSidx());
        log.debug("Search :" + searchField + " " + searchOper + " " + searchString);

        initContext();
        
        log.debug("Get Supplier List");
        try {
            list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        List<FrmSupplier> mySupplier = (List<FrmSupplier>) getAvailableItems();

        if (sord != null && sord.equalsIgnoreCase("asc")) {
            Collections.sort(mySupplier);
        }
        if (sord != null && sord.equalsIgnoreCase("desc")) {
            Collections.sort(mySupplier);
            Collections.reverse(mySupplier);
        }
        // Count all record (select count(*) from your_custumers)
        records = mySupplier.size();

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
                setGridModel(mySupplier.subList(0, totalrows));
            } else {
                setGridModel(mySupplier);
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
                    setGridModel((List<FrmSupplier>) getManager().findNotById(mySupplier, id, from, to));
                } else if (searchOper.equalsIgnoreCase("lt")) {
                    log.debug("search id lesser then " + id);
                    setGridModel((List<FrmSupplier>) getManager().findLesserAsId(mySupplier, id, from, to));
                } else if (searchOper.equalsIgnoreCase("gt")) {
                    log.debug("search id greater then " + id);
                    setGridModel((List<FrmSupplier>) getManager().findGreaterAsId(mySupplier, id, from, to));
                }
            } else {
                setGridModel(mySupplier);
            }
        }
        // Calculate total Pages
        total = (int) Math.ceil((double) records / (double) rows);
        
        return SUCCESS;
    }

    public String getJSON() {
        return execute();
    }

    public void setGridModel(List<FrmSupplier> gridModel) {
        setAvailableItems(gridModel);
    }

    @SuppressWarnings("unchecked")
    public List<FrmSupplier> getGridModel() {
        return (List<FrmSupplier>) getAvailableItems();
    }
}