package org.ktm.actions.web;

import java.util.List;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.ktm.actions.json.JsonAbstractAction;
import org.ktm.web.form.FrmProduct;
import org.ktm.web.manager.FormManager;
import org.ktm.web.manager.ServiceLocator;

@ParentPackage(value = "ktm-default")
public class JsonCatalogEntry extends JsonAbstractAction {

    private static final long serialVersionUID = 1145674274087102711L;
    private Logger            log              = Logger.getLogger(JsonCatalogEntry.class);

    @Actions({ @Action(value = "/catalog_entry_url", results = { @Result(location = "simpleecho.jsp", name = "success"), @Result(location = "simpleecho.jsp", name = "input") }) })
    public String execute() throws Exception {
        log.debug("Page " + getPage() + " Rows " + getRows() + " Sorting Order " + getSord() + " Index Row :" + getSidx());
        log.debug("Search :" + searchField + " " + searchOper + " " + searchString);

        log.debug("Get person List");
        list(this);
        

        return SUCCESS;
    }

    @SuppressWarnings("unchecked")
    public List<FrmProduct> getGridModel() {
        return (List<FrmProduct>) getAvailableItems();
    }

    @Override
    protected FormManager getManager() {
        return ServiceLocator.getProductManager();
    }

}
