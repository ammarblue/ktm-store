package org.ktm.web.tags;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class IterateTag extends SimpleTagSupport {

    private String      id         = null;
    private String      name       = null;
    private String      property   = null;

    private Object      collection = null;
    private Iterator<?> iterator   = null;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    @Override
    public void doTag() throws JspException, IOException {
        if (collection == null) {
            try {
                Object bean = getJspContext().findAttribute(name);
                if (bean == null) {
                    throw new JspException("no_attribute_name");
                }
                if (property == null) {
                    collection = bean;
                } else {
                    collection = PropertyUtils.getProperty(bean, property);
                }
                if (collection == null) {
                    throw new JspException("no_collection_object");
                }
            } catch (Exception ex) {
            }
        }

        if (collection instanceof Collection) {
            iterator = ((Collection<?>) collection).iterator();
        } else if (collection instanceof Iterator) {
            iterator = (Iterator<?>) collection;
        }

        int idx = 1;
        while (iterator.hasNext()) {
            Object element = iterator.next();
            getJspContext().setAttribute(id, element);
            getJspContext().setAttribute("id", "" + idx);
            getJspBody().invoke(null);
            idx++;
        }
    }

}
