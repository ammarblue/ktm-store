package org.ktm.actions.web;

import com.opensymphony.xwork2.Action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.ktm.actions.KTMActionSupport;

@ParentPackage("ktm-default")
@Results({ @Result(name = "tiles", location = "${redirectName}", type = "tiles"), @Result(name = Action.SUCCESS, location = "${redirectName}", type = "redirectAction") })
public class Index extends KTMActionSupport {

    private static final long serialVersionUID = 7430650672422777500L;
    private Logger log = Logger.getLogger(Index.class);

    private String redirectName;

    public String execute() {
        String result = Action.SUCCESS;

        redirectName = request.getParameter("page");
        String pageType = request.getParameter("t");
        if (pageType != null && redirectName != null) {
            if (pageType.equals("t")) {
                log.info("Go to tiles: " + redirectName);
                result = "tiles";
            }
        }
        if (redirectName == null) {
            log.info("Redirect to user-login");
            redirectName = "index.action?t=t&page=user-login";
        }
        return result;
    }

    public String getRedirectName() {
        return redirectName;
    }

}
