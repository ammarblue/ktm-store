package org.ktm.actions.login;

import java.security.NoSuchAlgorithmException;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.ktm.actions.FlowAction;
import org.ktm.encode.KTMCrypt;
import org.ktm.web.form.FrmAuthen;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

@ParentPackage(value = "ktm-json")
@InterceptorRef("jsonValidationWorkflowStack")
@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "loginuser", type = ValidatorType.FIELD, key = "errors.required"), @RequiredStringValidator(fieldName = "loginpassword", type = ValidatorType.FIELD, key = "errors.required") })
public class FlowDoLogin extends FlowAction {

    private static final long serialVersionUID = -5414549006750879297L;

    private String            loginuser;
    private String            loginpassword;

    @Actions({ @Action(value = "/login", results = { @Result(name = SUCCESS, location = "proc-get-user", type = "chain"), @Result(name = INPUT, location = "logon", type = "redirectAction") }) })
    public String execute() throws Exception {
        if (loginuser == null || loginpassword == null) {
            getActionErrors().add("User or Password is empty.");
        }
        if (loginuser.isEmpty() || loginpassword.isEmpty()) {
            getActionErrors().add("User or Password is empty.");
        }

        try {
            loginpassword = KTMCrypt.SHA1(loginuser + loginpassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        FrmAuthen form = new FrmAuthen();
        form.setUsername(loginuser);
        form.setPassword(loginpassword);
        ActionContext.getContext().put("authen", form);
        return SUCCESS;
    }

    public void validate() {
        if (getLoginuser().length() == 0) {
            addFieldError("loginuser", "User Name is required");
        }
        if (getLoginpassword().length() == 0) {
            addFieldError("loginpassword", getText("password.required"));
        }
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
}
