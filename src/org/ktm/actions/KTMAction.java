package org.ktm.actions;

import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.RequestAware;
import org.ktm.core.KTMContext;
import org.ktm.tag.auth.AuthException;
import org.ktm.tag.auth.AuthenticatorFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public abstract class KTMAction extends ActionSupport implements RequestAware, ParameterAware {

    private static final long       serialVersionUID = 1L;
    private Logger                  log              = Logger.getLogger(KTMAction.class);

    public static final String      FAILTURE         = "failture";
    public static final String      SUCCESS          = "success";
    public static final String      INPUT            = "input";
    public static final String      CURRENT_CONTEXT  = KTMAction.class.getName() + "_context";

    protected Map<String, Object>   request;
    protected Map<String, String[]> parameters;
    protected Map<String, Object>   session;

    protected void initContext() {
        KTMContext context = new KTMContext(this);
        ActionContext.getContext().put(CURRENT_CONTEXT, context);
    }

    @Override
    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }

    @Override
    public void setParameters(Map<String, String[]> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void addActionError(String message) {
        super.addActionMessage(message);
    }

    protected boolean isAuthorized() throws AuthException {
        if (AuthenticatorFactory.isUserLoggedIn()) {
            return true;
        }
        log.debug("This action is not authorized.");
        return false;
    }
}
