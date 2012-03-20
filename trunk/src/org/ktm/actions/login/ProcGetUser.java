package org.ktm.actions.login;

import javax.servlet.ServletContext;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.util.ServletContextAware;
import org.ktm.actions.ProcessAction;
import org.ktm.tag.auth.AuthException;
import org.ktm.tag.auth.Authenticator;
import org.ktm.tag.auth.AuthenticatorFactory;
import org.ktm.web.form.FrmAuthen;
import org.ktm.web.manager.FormManager;
import com.opensymphony.xwork2.ActionContext;

@ParentPackage(value = "ktm-process")
public class ProcGetUser extends ProcessAction implements ServletContextAware {

    private static final long serialVersionUID = -8630870526506736696L;
    private Logger            log              = Logger.getLogger(ProcGetUser.class);

    private ServletContext    servletContext   = null;

    @Override
    public void setServletContext(ServletContext context) {
        servletContext = context;
    }

    @Override
    protected FormManager getManager() {
        // TODO Auto-generated method stub
        return null;
    }

    @Actions({ @Action(value = "/proc-get-user", results = { @Result(name = SUCCESS, location = "is-valid-user", type = "chain") }) })
    public String execute() throws Exception {
        try {
            String authenticatorClassName = servletContext.getInitParameter("authenticator_class");
            Authenticator auth = (Authenticator) AuthenticatorFactory.getAuthComponent(this, servletContext, authenticatorClassName);
            FrmAuthen formAuthen = (FrmAuthen) ActionContext.getContext().get("authen");

            initContext();

            if (auth != null) {
                auth.doLogin(formAuthen.getUsername(), formAuthen.getPassword());
            }
        } catch (AuthException ex) {
            log.info(ex);
        }
        return SUCCESS;
    }
}
