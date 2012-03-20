package org.ktm.actions;

import com.opensymphony.xwork2.Action;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

@ParentPackage("ktm-flow")
@Results({ @Result(name = "tiles", location = "${redirectName}", type = "tiles"), @Result(name = Action.SUCCESS, location = "${redirectName}", type = "redirectAction") })
public class Index extends KTMAction {

    private static final long serialVersionUID = 7430650672422777500L;
    private Logger            log              = Logger.getLogger(Index.class);

    private String            redirectName;

    public String execute() {
        String result = Action.SUCCESS;

        String[] redirectNames = parameters.get("page");
        String[] pageTypes = parameters.get("t");

        try {
            String pageType = pageTypes[0];
            redirectName = redirectNames[0];

            if (pageType != null && redirectName != null) {
                if (pageType.equals("t")) {
                    log.info("Go to tiles: " + redirectName);
                    result = "tiles";
                }
                return result;
            }
        } catch (Exception ex) {}

        if (redirectName == null) {
            log.info("Redirect to user-login");
            redirectName = "logon";
        }
        return result;
    }

    public String getRedirectName() {
        return redirectName;
    }

}
