package org.ktm.actions.json;

import java.util.Collections;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.ktm.web.form.FrmCatalog;
import org.ktm.web.manager.ProductCatalogManager;
import org.ktm.web.manager.ServiceLocator;

@ParentPackage(value = "ktm-default")
public class JsonGridCatalog extends JsonAbstractAction {

    private static final long serialVersionUID = 1145674274087102711L;
    private Logger            log              = Logger.getLogger(JsonGridCatalog.class);

    @SuppressWarnings("unchecked")
    @Actions({ @Action(value = "/json-grid-catalog", results = { @Result(name = "success", type = "json"), @Result(name = INPUT, location = "database-product", type = "tiles") }) })
    public String execute() {
        log.debug("Page " + getPage() + " Rows " + getRows() + " Sorting Order " + getSord() + " Index Row :" + getSidx());
        log.debug("Search :" + searchField + " " + searchOper + " " + searchString);

        initContext();
        
        log.debug("Get Catalog List");
        try {
            list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<FrmCatalog> myCatalogs = (List<FrmCatalog>) getAvailableItems();

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
                    setGridModel((List<FrmCatalog>) getManager().findNotById(myCatalogs, id, from, to));
                } else if (searchOper.equalsIgnoreCase("lt")) {
                    log.debug("search id lesser then " + id);
                    setGridModel((List<FrmCatalog>) getManager().findLesserAsId(myCatalogs, id, from, to));
                } else if (searchOper.equalsIgnoreCase("gt")) {
                    log.debug("search id greater then " + id);
                    setGridModel((List<FrmCatalog>) getManager().findGreaterAsId(myCatalogs, id, from, to));
                }
            } else {
                setGridModel(myCatalogs);
            }
        }
        // Calculate total Pages
        total = (int) Math.ceil((double) records / (double) rows);
        
        return SUCCESS;
    }

    public String getJSON() {
        return execute();
    }

    public void setGridModel(List<FrmCatalog> gridModel) {
        setAvailableItems(gridModel);
    }

    @SuppressWarnings("unchecked")
    public List<FrmCatalog> getGridModel() {
        return (List<FrmCatalog>) getAvailableItems();
    }

    @Override
    protected ProductCatalogManager getManager() {
        return ServiceLocator.getProductCatalogManager();
    }

}
