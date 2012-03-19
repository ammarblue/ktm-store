package org.ktm.actions;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.ktm.tag.auth.AuthException;
import org.ktm.tag.auth.AuthenticatorFactory;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ValidationAware;

public abstract class KTMActionSupport extends ActionSupport implements ServletRequestAware, ValidationAware {

    private static final long     serialVersionUID = 1L;
    private Logger                log              = Logger.getLogger(KTMActionSupport.class);
    
    public static final String    FAILTURE         = "failture";
    public static final String    INPUT            = "input";

    protected HttpServletRequest  request;
    protected Map<String, Object> session;

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
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