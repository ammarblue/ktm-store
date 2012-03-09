package org.ktm.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.ktm.actions.json.JsonAbstractAction;
import org.ktm.dao.Dao;
import org.ktm.web.form.FrmProduct;

@ParentPackage(value = "ktm-default")
public class JsonCatalogEntry extends JsonAbstractAction {

    private static final long serialVersionUID = 1145674274087102711L;
    private Logger            log              = Logger.getLogger(JsonCatalogEntry.class);

    private List<FrmProduct>  gridModel;

    @Actions({ @Action(value = "/catalog_entry_url", results = { @Result(location = "simpleecho.jsp", name = "success"), @Result(location = "simpleecho.jsp", name = "input") }) })
    public String execute() throws Exception {
        log.debug("Page " + getPage() + " Rows " + getRows() + " Sorting Order " + getSord() + " Index Row :" + getSidx());
        log.debug("Search :" + searchField + " " + searchOper + " " + searchString);

        log.debug("Get person List");
        List<FrmProduct> myPersons = new ArrayList<FrmProduct>();

        return SUCCESS;
    }

    @Override
    protected Dao getDao() {
        return null;
    }

    public List<FrmProduct> getGridModel() {
        return gridModel;
    }

    public void setGridModel(List<FrmProduct> gridModel) {
        this.gridModel = gridModel;
    }

}
