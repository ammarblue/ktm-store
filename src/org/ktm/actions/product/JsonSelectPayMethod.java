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
import org.ktm.actions.ListValue;
import org.ktm.web.manager.FormManager;

public class JsonSelectPayMethod extends JsonGridFieldsAction {

    private static final long serialVersionUID = -8222296940094941679L;
    private static final Logger  log              = Logger.getLogger(JsonSelectPayMethod.class);

    private List<String>         payMethodList;
    private List<ListValue>      payMethodObjList;
    private Map<String, String>  payMethodMap;

    // private String language;

    @Actions({ @Action(value = "/json-select-paymethod", results = { @Result(name = "success", type = "json") }) })
    public String execute() {
        log.debug("Get Json pay method");

        initContext();
        
        payMethodList = new ArrayList<String>();
        payMethodObjList = new ArrayList<ListValue>();
        payMethodMap = new HashMap<String, String>();

        int max = Integer.parseInt(getText("page.common.paymethod_max"));
        for (int i=1; i<=max; i++) {
            String txt = getText("page.common.paymethod_"+i);
            payMethodList.add(txt);
            payMethodObjList.add(new ListValue(String.valueOf(i), txt));
            payMethodMap.put(String.valueOf(i), txt);
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

    public List<String> getPayMethodList() {
        return payMethodList;
    }

    public void setPayMethodList(List<String> payMethodList) {
        this.payMethodList = payMethodList;
    }

    public Map<String, String> getPayMethodMap() {
        return payMethodMap;
    }

    public void setPayMethodMap(Map<String, String> payMethodMap) {
        this.payMethodMap = payMethodMap;
    }

    public List<ListValue> getPayMethodObjList() {
        return payMethodObjList;
    }

    public void setPayMethodObjList(List<ListValue> payMethodObjList) {
        this.payMethodObjList = payMethodObjList;
    }

}
