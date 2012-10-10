package org.ktm.stock.tags;

import java.io.IOException;
import java.util.Iterator;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import org.ktm.web.tabs.Functions;

public class NavSubMenuTag extends SimpleTagSupport {

    @Override
    public void doTag() throws IOException {
        Object beanObj = getJspContext().findAttribute("page");
        if (beanObj != null && beanObj instanceof String) {
            String page = (String) beanObj;
            if (!page.isEmpty()) {
                JspWriter out = getJspContext().getOut();

                Iterator<MenuItem> menus = SubMenu.getMenu(page).iterator();

                out.println("<h4>" + Functions.getText("menu") + "</h4>");
                out.println("<ul>");
                while (menus.hasNext()) {
                    MenuItem menu = menus.next();
                    out.println("  <li><a href='" + menu.getAction() + "'>" + Functions.getText(menu.getName()) + "</a></li>");
                }
                out.println("</ul>");
            }
        }
    }
}
