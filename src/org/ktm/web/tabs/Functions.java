package org.ktm.web.tabs;

import java.util.Collection;
import org.ktm.utils.Localizer;

public class Functions {

    public static String getText(String name) {
        return Localizer.getString(name);
    }

    public static Object getBeanProperty(Object obj, String name) {
        return PropertyUtils.getProperty(obj, name);
    }

    public static Integer length(Collection<?> cols) {
        return cols.size();
    }

    public static Boolean isEmpty(String text) {
        return text.isEmpty();
    }

    public static Boolean isEmptyCollection(Collection<?> colls) {
        return colls.size() <= 0;
    }

    public static Boolean equal(String val1, String val2) {
        return val1.equals(val2);
    }
}
