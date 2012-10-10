package org.ktm.web.tabs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.MissingResourceException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import org.ktm.utils.Localizer;

public class OptionsTag extends SimpleTagSupport {

    private String bean       = null;
    private String selected   = null;
    private String value      = null;
    private String label      = null;
    private String collection = null;

    public void setBean(String bean) {
        this.bean = bean;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getSelected() {
        return selected;
    }

    @Override
    public void doTag() throws JspException {
        Iterator<?> iterator = null;
        boolean isLabel = false;

        Object beanObj = getJspContext().findAttribute(bean);
        if (beanObj == null) {
            throw new JspException("no_attribute_name");
        }

        if (label.indexOf(',') > 0) {
            String[] names = label.split(",");
            ArrayList<String> newList = new ArrayList<String>();
            for (String str : names) {
                newList.add(str);
            }
            iterator = newList.iterator();
            isLabel = true;
        } else {
            Object colln = PropertyUtils.getProperty(beanObj, collection);
            if (colln instanceof Collection) {
                iterator = ((Collection<?>) colln).iterator();
            } else if (colln instanceof Iterator) {
                iterator = (Iterator<?>) colln;
            }
        }

        JspWriter out = getJspContext().getOut();
        try {
            Object selectedObj = PropertyUtils.getProperty(beanObj, selected);

            if (isLabel) {
                while (iterator.hasNext()) {
                    Object val = iterator.next();
                    String selected = PropertyUtils.isEqual(selectedObj, val) ? "selected" : "";
                    out.println("<option " + selected + " value=\"" + val + "\">" + getLabelString(val.toString()) + "</option>");
                }
            } else {
                while (iterator.hasNext()) {
                    Object item = iterator.next();
                    Object val = PropertyUtils.getProperty(item, value);
                    Object lbl = PropertyUtils.getProperty(item, label);
                    String selected = PropertyUtils.isEqual(selectedObj, val) ? "selected" : "";
                    out.println("<option " + selected + " value=\"" + val + "\">" + getLabelString(lbl.toString()) + "</option>");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getLabelString(String label) {
        String result = label;
        try {
            result = Localizer.getString(label);
        } catch (MissingResourceException mre) {

        }
        return result;
    }

}
