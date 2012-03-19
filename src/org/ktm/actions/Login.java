package org.ktm.actions;

import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletContext;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
// import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.util.ServletContextAware;
import org.ktm.encode.KTMCrypt;
import org.ktm.tag.auth.AuthException;
import org.ktm.tag.auth.Authenticator;
import org.ktm.tag.auth.AuthenticatorFactory;
import org.ktm.web.manager.FormManager;

// import com.opensymphony.xwork2.validator.annotations.ExpressionValidator;
// import com.opensymphony.xwork2.validator.annotations.Validations;
// import com.opensymphony.xwork2.validator.annotations.ValidatorType;
// import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

@ParentPackage(value = "ktm-default")
// @InterceptorRef("jsonValidationWorkflowStack")
// @Validations(requiredStrings = {
// @RequiredStringValidator(fieldName = "loginuser", type = ValidatorType.FIELD,
// message = "Login User is required"),
// @RequiredStringValidator(fieldName = "loginpassword", type =
// ValidatorType.FIELD, message = "Password is required")
// }, expressions = {
// @ExpressionValidator(expression = "loginpassword.trim().length() > 1",
// message = "Password must more than 4 character"),
// })
public class Login extends KTMAction implements ServletContextAware {

    private static final long serialVersionUID = 7968544374444173511L;
    private Logger            log              = Logger.getLogger(Login.class);

    private String            loginuser;
    private String            loginpassword;
    private String            nextAction;

    private ServletContext    servletContext;

    @Actions({ @Action(value = "/login", results = { @Result(name = SUCCESS, location = "${nextAction}", type = "tiles"), @Result(name = INPUT, location = "user-login", type = "tiles") }) })
    public String execute() throws Exception {
        String result = INPUT;
        log.info("Username: " + loginuser);

        initContext();
        
        try {
            loginpassword = KTMCrypt.SHA1(loginuser + loginpassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        try {
            String authenticatorClassName = servletContext.getInitParameter("authenticator_class");
            Authenticator auth = (Authenticator) AuthenticatorFactory.getAuthComponent(this, servletContext, authenticatorClassName);
            if (auth != null) {
                auth.doLogin(loginuser, loginpassword);
                if (auth.isUserLoggedIn()) {
                    log.info("Login success.");

                    nextAction = AuthenticatorFactory.obtainForward(AuthenticatorFactory.restoreRequestContext(request));

                    if (nextAction.isEmpty()) {
                        nextAction = "file";
                    }
                    result = SUCCESS;
                } else {
                    log.info("Login failed !!");
                }
            }
        } catch (AuthException ex) {
            log.info(ex);
        }

        return result;
    }
    
    @Actions({ @Action(value = "/logout", results = { @Result(name = SUCCESS, location = "pages/logon.jsp", type = "redirect") }) })
    public String logout() throws Exception {
        log.info("Processing logout...");
        String authenticatorClassName = servletContext.getInitParameter("authenticator_class");
        Authenticator auth = AuthenticatorFactory.getAuthComponentNoCreate(servletContext, authenticatorClassName);

        if (auth != null) {
            auth.doLogout();
        }
        return SUCCESS;
    }

    @Override
    public void setServletContext(ServletContext context) {
        servletContext = context;
    }

    public String getLoginuser() {
        return loginuser;
    }

    public void setLoginuser(String loginuser) {
        this.loginuser = loginuser;
    }

    public String getLoginpassword() {
        return loginpassword;
    }

    public void setLoginpassword(String loginpassword) {
        this.loginpassword = loginpassword;
    }

    public String getNextAction() {
        return nextAction;
    }

    @Override
    protected FormManager getManager() {
        return null;
    }
}
