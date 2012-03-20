package org.ktm.actions.crud;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
import org.ktm.actions.CrudAction;
import org.ktm.web.manager.FormManager;

public class CrudGridSupplier extends CrudAction {

    private static final long serialVersionUID = -346563543980200104L;

    @Actions({ @Action(value = "/crud-grid-supplier", results = { @Result(location = "simpleecho.jsp", name = "success"), @Result(location = "simpleecho.jsp", name = "input") }) })
    public String execute() throws Exception {
        return SUCCESS;
    }
    
    @Override
    protected FormManager getManager() {
        // TODO Auto-generated method stub
        return null;
    }

}
