package org.ktm.web.utils;

import java.util.HashMap;
import java.util.Map;

public class HeaderUtils {

    private NavType                choice;
    private Themes                 currentTheme = Themes.south_street;

    private Map<NavType, String[]> cssFiles     = new HashMap<NavType, String[]>();
    private Map<NavType, String[]> jsFiles      = new HashMap<NavType, String[]>();
    private Map<NavType, String>   title        = new HashMap<NavType, String>();
    private Map<NavType, String>   theme        = new HashMap<NavType, String>();

    public HeaderUtils() {

        choice = NavType.file;

        NavType[] types = NavType.values();

        // common theme
        for (NavType t : types) {
            title.put(t, "app.title");
            theme.put(t, currentTheme.getName());
        }

        cssFiles.put(NavType.file, new String[] { "./style/layout.css", "./style/nav.css" });
        cssFiles.put(NavType.edit, new String[] { "./style/layout.css", "./style/nav.css" });
        cssFiles.put(NavType.database, new String[] { "./style/layout.css", "./style/nav.css" });
        cssFiles.put(NavType.trans, new String[] { "./style/layout.css", "./style/nav.css" });
        cssFiles.put(NavType.other, new String[] { "./style/layout.css", "./style/nav.css" });
        cssFiles.put(NavType.report, new String[] { "./style/layout.css", "./style/nav.css" });

        jsFiles.put(NavType.file, new String[] { "./js/nav.js" });
        jsFiles.put(NavType.edit, new String[] { "./js/nav.js" });
        jsFiles.put(NavType.database, new String[] { "./js/nav.js" });
        jsFiles.put(NavType.trans, new String[] { "./js/nav.js" });
        jsFiles.put(NavType.other, new String[] { "./js/nav.js" });
        jsFiles.put(NavType.report, new String[] { "./js/nav.js" });
    }

    public void setChoice(NavType choice) {
        this.choice = choice;
    }

    public String[] getCssFiles() {
        return cssFiles.get(choice);
    }

    public String[] getJsFiles() {
        return jsFiles.get(choice);
    }

    public String getTitle() {
        return title.get(choice);
    }

    public String getTheme() {
        return theme.get(choice);
    }

}
