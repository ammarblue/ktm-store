package org.ktm.actions.product;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.ktm.actions.CrudAction;
import org.ktm.web.manager.OrderManager;
import org.ktm.web.manager.ServiceLocator;

public class CrudOrder extends CrudAction {

    private static final long serialVersionUID = 1L;

    @Override
    protected OrderManager getManager() {
        return (OrderManager) ServiceLocator.getOrderManager();
    }

    @Action(value = "/crud-order", results = {
      @Result(location = "simpleecho.jsp", name = "success")
    })
    public String execute() throws Exception {
        return SUCCESS;
    }
}
