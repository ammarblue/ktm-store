package org.ktm.tags;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class MessageManager {

    public static enum eLanguage {
        THAI, ENGLISH
    };

    private static Locale locale_th = new Locale("th", "TH");
    private static Locale locale_en = new Locale("en", "US");
    private static Locale locale    = locale_th;

    private static ResourceBundle getBundle() {
        return ResourceBundle.getBundle("messages", locale);
    }

    public static void switchLocale(eLanguage lang) {
        if (lang == eLanguage.THAI) {
            locale = locale_th;
        } else if (lang == eLanguage.ENGLISH) {
            locale = locale_en;
        }
    }

    public static String getString(String name) {
        String result = name;
        try {
            result = getBundle().getString(name);
        } catch (MissingResourceException mre) {

        }
        return result;
    }

}
