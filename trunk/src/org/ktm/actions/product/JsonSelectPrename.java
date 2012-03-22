package org.ktm.actions.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
import org.ktm.actions.JsonGridFieldsAction;
import org.ktm.web.manager.FormManager;

public class JsonSelectPrename extends JsonGridFieldsAction {

    private static final long serialVersionUID = -8222296940094941679L;
    private static final Logger  log              = Logger.getLogger(JsonSelectPrename.class);

    private List<String>         prenameList;
    private Map<Integer, String> prenameMap;

    // private String language;

    @Actions({ @Action(value = "/json-select-prename", results = { @Result(name = "success", type = "json") }) })
    public String execute() {
        log.debug("Get Json Prename");

        initContext();
        
        prenameList = new ArrayList<String>();
        prenameMap = new HashMap<Integer, String>();

        int max = Integer.parseInt(getText("page.common.prename_max"));
        for (int i=1; i<=max; i++) {
            String txt = getText("page.common.prename_"+i);
            prenameList.add(txt);
            prenameMap.put(i, txt);
        }
        return SUCCESS;
    }

    public String getJSON() {
        return execute();
    }

    @Override
    protected FormManager getManager() {
        // TODO Auto-generated method stub
        return null;
    }

    public List<String> getPrenameList() {
        return prenameList;
    }

    public void setPrenameList(List<String> prenameList) {
        this.prenameList = prenameList;
    }

    public Map<Integer, String> getPrenameMap() {
        return prenameMap;
    }

    public void setPrenameMap(Map<Integer, String> prenameMap) {
        this.prenameMap = prenameMap;
    }

}
