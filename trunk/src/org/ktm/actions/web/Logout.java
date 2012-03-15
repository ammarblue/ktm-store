package org.ktm.actions.web;

import javax.servlet.ServletContext;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.util.ServletContextAware;
import org.ktm.actions.KTMAction;
import org.ktm.tag.auth.Authenticator;
import org.ktm.tag.auth.AuthenticatorFactory;
import org.ktm.web.manager.FormManager;

@ParentPackage(value = "ktm-default")
public class Logout extends KTMAction implements ServletContextAware {

    private static final long serialVersionUID = 592209854904210873L;
    private Logger            log              = Logger.getLogger(Logout.class);

    private ServletContext    servletContext;

    @Actions({ @Action(value = "/logout", results = { @Result(name = INPUT, location = "user-login", type = "tiles") }) })
    public String execute() throws Exception {
        log.info("Processing logout...");
        String authenticatorClassName = servletContext.getInitParameter("authenticator_class");
        Authenticator auth = AuthenticatorFactory.getAuthComponentNoCreate(servletContext, authenticatorClassName);

        if (auth != null) {
            auth.doLogout();
        }
        return INPUT;
    }

    @Override
    public void setServletContext(ServletContext context) {
        servletContext = context;
    }

    @Override
    protected FormManager getManager() {
        return null;
    }

}
