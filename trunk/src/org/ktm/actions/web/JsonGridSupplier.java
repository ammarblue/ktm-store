package org.ktm.actions.web;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.ktm.actions.KTMAction;
import org.ktm.web.manager.FormManager;

@ParentPackage(value = "ktm-default")
public class JsonGridSupplier extends KTMAction {

    private static final long   serialVersionUID = 97711804233910039L;
    private static final Logger log              = Logger.getLogger(JsonGridSupplier.class);


    @Actions({ @Action(value = "/json-select-catalog", results = { @Result(name = "success", type = "json") }) })
    public String execute() {
        log.debug("Get Json Supplier");

        return SUCCESS;
    }
    
    @Override
    protected FormManager getManager() {
        // TODO Auto-generated method stub
        return null;
    }

}
