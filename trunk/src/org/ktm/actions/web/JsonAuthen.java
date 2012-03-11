package org.ktm.actions.web;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.ktm.actions.json.JsonAbstractAction;
import org.ktm.web.form.FrmAuthen;
import org.ktm.web.manager.FormManager;
import org.ktm.web.manager.ServiceLocator;

@ParentPackage(value = "ktm-default")
public class JsonAuthen extends JsonAbstractAction {

    private static final long serialVersionUID = 8072293334749008043L;
    private Logger log = Logger.getLogger(JsonAuthen.class);

    @Actions({ @Action(value = "/jsonauthen", results = { @Result(name = "success", type = "json") }) })
    @SuppressWarnings("unchecked")
    public String execute() {
        log.debug("Page " + getPage() + " Rows " + getRows() + " Sorting Order " + getSord() + " Index Row :" + getSidx());
        log.debug("Search :" + searchField + " " + searchOper + " " + searchString);

        log.debug("Get authen List");
        try {
            list(this);
        } catch (Exception e) {

        }
        
        List<FrmAuthen> myAuthens = (List<FrmAuthen>) getAvailableItems();

        if (sord != null && sord.equalsIgnoreCase("asc")) {
            Collections.sort(myAuthens);
        }
        if (sord != null && sord.equalsIgnoreCase("desc")) {
            Collections.sort(myAuthens);
            Collections.reverse(myAuthens);
        }

        // Count all record (select count(*) from your_custumers)
        records = myAuthens.size();

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
                setGridModel(myAuthens.subList(0, totalrows));
            } else {
                setGridModel(myAuthens);
            }
        } else {
            if (searchString != null && searchOper != null) {
                int id = Integer.parseInt(searchString);
                if (searchOper.equalsIgnoreCase("eq")) {
                    log.debug("search id equals " + id);
                    //List<FrmAuthen> cList = new ArrayList<FrmAuthen>();
                    // TODO: Search by id
                } else if (searchOper.equalsIgnoreCase("ne")) {
                    log.debug("search id not " + id);
                    setGridModel((List<FrmAuthen>) getManager().findNotById(myAuthens, id, from, to));
                } else if (searchOper.equalsIgnoreCase("lt")) {
                    log.debug("search id lesser then " + id);
                    setGridModel((List<FrmAuthen>) getManager().findLesserAsId(myAuthens, id, from, to));
                } else if (searchOper.equalsIgnoreCase("gt")) {
                    log.debug("search id greater then " + id);
                    setGridModel((List<FrmAuthen>) getManager().findGreaterAsId(myAuthens, id, from, to));
                }
            } else {
                // setGridModel((List<FrmAuthen>) ((PersonDao)
                // getDao()).getSubList(myAuthens, from, to));
                setGridModel(myAuthens);
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
    public List<FrmAuthen> getGridModel() {
        return (List<FrmAuthen>) getAvailableItems();
    }

    public void setGridModel(List<FrmAuthen> gridModel) {
        setAvailableItems(gridModel);
    }

    @Override
    protected FormManager getManager() {
        return ServiceLocator.getAuthenManager();
    }

}
