package org.ktm.actions.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.ktm.actions.json.JsonAbstractAction;
import org.ktm.web.form.FrmPerson;
import org.ktm.web.manager.FormManager;
import org.ktm.web.manager.ServiceLocator;

@ParentPackage(value = "ktm-default")
public class JsonPerson extends JsonAbstractAction {

    private static final long serialVersionUID = 8072293334749008043L;
    private Logger log = Logger.getLogger(JsonPerson.class);

    @Actions({ @Action(value = "/jsonperson", results = { @Result(name = "success", type = "json"), @Result(name = INPUT, location = "user-login", type = "tiles") }) })
    @SuppressWarnings("unchecked")
    public String execute() {
        log.debug("Page " + getPage() + " Rows " + getRows() + " Sorting Order " + getSord() + " Index Row :" + getSidx());
        log.debug("Search :" + searchField + " " + searchOper + " " + searchString);

        log.debug("Get person List");
        try {
            list(this);
        } catch (Exception e) {
            
        }
        List<FrmPerson> myPersons = (List<FrmPerson>) getAvailableItems();
        
        if (sord != null && sord.equalsIgnoreCase("asc")) {
            Collections.sort(myPersons);
        }
        if (sord != null && sord.equalsIgnoreCase("desc")) {
            Collections.sort(myPersons);
            Collections.reverse(myPersons);
        }

        // Count all record (select count(*) from your_custumers)
        records = myPersons.size();

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
                setGridModel(myPersons.subList(0, totalrows));
            } else {
                // All Custumer
                setGridModel(myPersons);
            }
        } else {
            // Search Custumers
            if (searchString != null && searchOper != null) {
                int id = Integer.parseInt(searchString);
                if (searchOper.equalsIgnoreCase("eq")) {
                    log.debug("search id equals " + id);
                    List<FrmPerson> cList = new ArrayList<FrmPerson>();
                    FrmPerson fperson = (FrmPerson) getManager().get(this, id);

                    if (fperson != null) {
                        cList.add(fperson);
                    }

                    setGridModel(cList);
                } else if (searchOper.equalsIgnoreCase("ne")) {
                    log.debug("search id not " + id);
                    setGridModel((List<FrmPerson>) getManager().findNotById(myPersons, id, from, to));
                } else if (searchOper.equalsIgnoreCase("lt")) {
                    log.debug("search id lesser then " + id);
                    setGridModel((List<FrmPerson>) getManager().findLesserAsId(myPersons, id, from, to));
                } else if (searchOper.equalsIgnoreCase("gt")) {
                    log.debug("search id greater then " + id);
                    setGridModel((List<FrmPerson>) getManager().findGreaterAsId(myPersons, id, from, to));
                }
            } else {
                setGridModel((List<FrmPerson>) getManager().getSubList(myPersons, from, to));
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
    public List<FrmPerson> getGridModel() {
        return (List<FrmPerson>) getAvailableItems();
    }

    private void setGridModel(List<FrmPerson> subList) {
        setAvailableItems(subList);
    }

    @Override
    protected FormManager getManager() {
        return ServiceLocator.getPersonManager();
    }
}
