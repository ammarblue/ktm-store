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
public class FlowIsValidUser extends FlowAction {

    private static final long serialVersionUID = 4820745866501859353L;
    private Logger            log              = Logger.getLogger(FlowIsValidUser.class);

    private String nextAction;
    @Actions({ @Action(value = "/is-valid-user", results = { @Result(name = SUCCESS, location = "${nextAction}", type = "tiles"), @Result(name = FAILTURE, location = "logon", type = "redirectAction") }) })
    public String execute() throws Exception {
        String result = FAILTURE;
        Authenticator auth = AuthenticatorFactory.getAuthComponentNoCreate();
        if (auth != null && auth.isUserLoggedIn()) {
            log.info("Login success.");

            nextAction = AuthenticatorFactory.obtainForward(AuthenticatorFactory.restoreRequestContext(request));

            if (nextAction.isEmpty()) {
                // TODO: fix currect page
                nextAction = "file";
            }
            result = SUCCESS;
        } else {
            log.info("Login failed !!");
        }
        return result;
    }

    public String getNextAction() {
        return nextAction;
    }

    public void setNextAction(String nextAction) {
        this.nextAction = nextAction;
    }
}
