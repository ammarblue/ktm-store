package org.ktm.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

public class Localizer {

    private static Logger         log                          = Logger.getLogger(Localizer.class);

    protected static final String RESOURCE_BUNDLE_NAME_DEFAULT = "message";

    public static String getClassName(Class<?> clazz) {
        String className = clazz.getName();
        int i = className.lastIndexOf('.');
        if (i >= 0) {
            className = className.substring(i + 1);
        }
        return className;
    }

    public static String getMessage(String requestedMessageId, Class<?> requestingClass) {
        return getMessage(requestedMessageId, new Object[] {}, Locale.getDefault(), requestingClass, RESOURCE_BUNDLE_NAME_DEFAULT);
    }

    public static String getMessage(String requestedMessageId, Locale locale, Class<?> requestingClass) {
        return getMessage(requestedMessageId, new Object[] {}, locale, requestingClass, RESOURCE_BUNDLE_NAME_DEFAULT);
    }

    public static String getMessage(String requestedMessageId, Locale locale, Class<?> requestingClass, String unqualifiedResourceBundleName) {
        return getMessage(requestedMessageId, new Object[] {}, locale, requestingClass, unqualifiedResourceBundleName);
    }

    public static String getMessage(String requestedMessageId, Object[] messageFormatArguments, Locale locale, Class<?> requestingClass) {
        return getMessage(requestedMessageId, messageFormatArguments, locale, requestingClass, RESOURCE_BUNDLE_NAME_DEFAULT);
    }

    public static String getMessage(String requestedMessageId, Object[] messageFormatArguments, Locale locale, Class<?> requestingClass, String unqualifiedResourceBundleName) {
        String returnMessage = null;

        ResourceBundle resourceBundle = getResourceBundle(locale, requestingClass, unqualifiedResourceBundleName);

        if (resourceBundle != null) {
            try {
                String requestedMessageIdFormatString = resourceBundle.getString(requestedMessageId);
                MessageFormat requestedMessageIdFormat = new MessageFormat(requestedMessageIdFormatString);
                returnMessage = requestedMessageIdFormat.format(messageFormatArguments);

            } catch (MissingResourceException e) {
                log.info("Key in resource bundle is unavailable for class: " + requestingClass.getName() + "; locale: " + locale.toString() + "; requestedMessageId (as key): " + requestedMessageId + ". Returning null. Client code may apply manual formatting to the message.");
            }
        }
        return returnMessage;
    }

    private static ResourceBundle getResourceBundle(Locale locale, Class<?> requestingClass, String unqualifiedResourceBundleName) {
        ResourceBundle resourceBundle = null;

        try {
            String qualifiedResourceBundleName = getQualifiedResourceBundleName(requestingClass, unqualifiedResourceBundleName);
            resourceBundle = ResourceBundle.getBundle(qualifiedResourceBundleName, locale, requestingClass.getClassLoader());

        } catch (MissingResourceException e) {
            log.info("Resource bundle unavailable for class: " + requestingClass.getName() + "; locale: " + locale.toString());
        }
        return resourceBundle;
    }

    private static String getQualifiedResourceBundleName(Class<?> clazz, String unqualifiedResourceBundleName) {
        return (clazz.getPackage().getName() + "." + unqualifiedResourceBundleName);
    }
}
