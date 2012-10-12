package org.ktm.stock.tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import org.ktm.authen.AuthenticatorFactory;
import org.ktm.web.tags.Functions;

public class NavMenuTag extends SimpleTagSupport {

    @SuppressWarnings("unused")
    private String       adminRole;
    @SuppressWarnings("unused")
    private String       employeeRole;

    private List<String> adminRoles    = new ArrayList<String>();
    private List<String> employeeRoles = new ArrayList<String>();

    public void setAdminRole(String adminRole) {
        this.adminRole = adminRole;

        adminRoles.clear();

        String[] roles = adminRole.split(",");
        for (String role : roles) {
            adminRoles.add(role);
        }
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;

        employeeRoles.clear();

        String[] roles = employeeRole.split(",");
        for (String role : roles) {
            employeeRoles.add(role);
        }
    }

    @Override
    public void doTag() throws IOException {
        PageContext pageContext = (PageContext) getJspContext();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

        boolean inAdminRole = AuthenticatorFactory.isUserInRoles(request, adminRoles);
        boolean inEmployeeRole = AuthenticatorFactory.isUserInRoles(request, employeeRoles);

        Object beanObj = getJspContext().findAttribute("page");
        if (beanObj != null && beanObj instanceof String) {
            String page = (String) beanObj;
            if (!page.isEmpty()) {
                JspWriter out = getJspContext().getOut();
                out.println("<nav id=\"nav\">");
                out.println("  <div class=\"ym-hlist\" >");
                out.println("    <ul>");
                if (inAdminRole) {
                    if (page.equals("main")) {
                        out.println("      <li class=\"active\"><strong>" + Functions.getText("nav.file") + "</strong></li>");
                    } else {
                        out.println("      <li><a href=\"Main?page=main\">" + Functions.getText("nav.file") + "</a></li>");
                    }
                }
                if (inAdminRole) {
                    if (page.equals("edit")) {
                        out.println("      <li class=\"active\"><strong>" + Functions.getText("nav.edit") + "</strong></li>");
                    } else {
                        out.println("      <li><a href=\"Main?page=edit\">" + Functions.getText("nav.edit") + "</a></li>");
                    }
                }
                if (inAdminRole) {
                    if (page.equals("database")) {
                        out.println("      <li class=\"active\"><strong>" + Functions.getText("nav.database") + "</strong></li>");
                    } else {
                        out.println("      <li><a href=\"Main?page=database\">" + Functions.getText("nav.database") + "</a></li>");
                    }
                }
                if (inEmployeeRole) {
                    if (page.equals("transaction")) {
                        out.println("      <li class=\"active\"><strong>" + Functions.getText("nav.transaction") + "</strong></li>");
                    } else {
                        out.println("      <li><a href=\"Main?page=transaction\">" + Functions.getText("nav.transaction") + "</a></li>");
                    }
                }
                if (inEmployeeRole) {
                    if (page.equals("other")) {
                        out.println("      <li class=\"active\"><strong>" + Functions.getText("nav.other") + "</strong></li>");
                    } else {
                        out.println("      <li><a href=\"Main?page=other\">" + Functions.getText("nav.other") + "</a></li>");
                    }
                }
                if (inEmployeeRole) {
                    if (page.equals("report")) {
                        out.println("      <li class=\"active\"><strong>" + Functions.getText("nav.report") + "</strong></li>");
                    } else {
                        out.println("      <li><a href=\"Main?page=report\">" + Functions.getText("nav.report") + "</a></li>");
                    }
                }
                out.println("    </ul>");
                out.println("    <form class=\"ym-searchform\">");
                out.println("      <input class=\"ym-searchfield\" type=\"search\" placeholder='" + Functions.getText("page.btn.search") + "...' />");
                out.println("      <input class=\"ym-searchbutton\" type=\"submit\" value='" + Functions.getText("page.btn.search") + "' />");
                out.println("    </form>");
                out.println("  </div>");
                out.println("</nav>");
            }
        }
    }
}
