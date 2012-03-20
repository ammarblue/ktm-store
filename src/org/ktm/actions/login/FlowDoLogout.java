package org.ktm.actions.login;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.ktm.actions.FlowAction;
import org.ktm.tag.auth.Authenticator;
import org.ktm.tag.auth.AuthenticatorFactory;

@ParentPackage(value = "ktm-flow")
public class FlowDoLogout extends FlowAction {

    private static final long serialVersionUID = 7968544374444173511L;
    private Logger            log              = Logger.getLogger(FlowDoLogout.class);

    @Actions({ @Action(value = "/logout", results = { @Result(name = SUCCESS, location = "logon", type = "redirectAction") }) })
    public String execute() throws Exception {
        log.info("Processing logout...");
        Authenticator auth = AuthenticatorFactory.getAuthComponentNoCreate();

        if (auth != null) {
            auth.doLogout();
        }
        return SUCCESS;
    }

}
