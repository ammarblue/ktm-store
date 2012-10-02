package org.ktm.core;

import javax.servlet.http.HttpSession;

public class KTMContext {
    private static HttpSession session    = null;

    public static final String APPCONTEXT = "context";
    public static KTMContext   instance   = null;

    private String             jspHeader;
    private String             jspFooter;

    public String getJspHeader() {
        return jspHeader;
    }

    public String getJspFooter() {
        return jspFooter;
    }

    public void setJspHeader(String jspHeader) {
        this.jspHeader = jspHeader;
    }

    public void setJspFooter(String jspFooter) {
        this.jspFooter = jspFooter;
    }

    public static HttpSession getSession() {
        return session;
    }

    public static void setSession(HttpSession session) {
        KTMContext.session = session;
    }

    public static KTMContext getInstance() {
        if (instance == null) {
            instance = new KTMContext();
        }
        return instance;
    }
}
