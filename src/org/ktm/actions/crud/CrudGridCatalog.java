package org.ktm.actions.crud;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
import org.ktm.actions.AbstractCRUDAction;
import org.ktm.web.form.FrmCatalog;
import org.ktm.web.manager.ProductCatalogManager;
import org.ktm.web.manager.ServiceLocator;

public class CrudGridCatalog extends AbstractCRUDAction {

    private static final long serialVersionUID = -7492977888229483777L;
    private Logger            log              = Logger.getLogger(CrudGridCatalog.class);

    private String            oper             = "";
    private String            id;
    private String            identifier;
    private String            name;

    @Actions({ @Action(value = "/crud-grid-catalog", results = { @Result(location = "simpleecho.jsp", name = "success"), @Result(location = "simpleecho.jsp", name = "input") }) })
    public String execute() throws Exception {
        log.debug("id :" + id);
        log.debug("identifier :" + identifier);
        log.debug("name :" + name);

        initContext();
        
        if (oper.equalsIgnoreCase("del")) {

        } else {
            FrmCatalog pcatalog = new FrmCatalog();
            pcatalog.setIdentifier(identifier);
            pcatalog.setName(name);

            if (oper.equalsIgnoreCase("add")) {
                log.debug("Add new ProductCatalog");
                pcatalog.setNew(true);
            } else if (oper.equalsIgnoreCase("edit")) {
                log.debug("Edit ProductCatalog");
                pcatalog.setId(Integer.parseInt(id));
                pcatalog.setNew(false);
            }

            getManager().addOrUpdate(pcatalog);
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

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected ProductCatalogManager getManager() {
        return ServiceLocator.getProductCatalogManager();
    }

}
