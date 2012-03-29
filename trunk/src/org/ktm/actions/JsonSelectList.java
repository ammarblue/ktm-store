package org.ktm.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
import org.ktm.web.form.FrmCatalog;
import org.ktm.web.manager.FormManager;
import org.ktm.web.manager.ServiceLocator;

public class JsonSelectList extends JsonGridFieldsAction {

    private static final long   serialVersionUID = 1L;
    private static final Logger log              = Logger.getLogger(JsonSelectList.class);

    private List<String>        selectList       = null;
    private List<ListValue>     selectObjList    = null;
    private Map<String, String> selectMap        = null;
    private String              listType;

    @Override
    protected FormManager getManager() {
        FormManager result = null;
        if (listType.equals("catalogList")) {
            result = ServiceLocator.getProductCatalogManager();
        }
        return result;
    }

    public String getJSON() {
        return execute();
    }

    @Actions({ @Action(value = "/json-select-list", results = { @Result(name = "success", type = "json") }) })
    public String execute() {
        log.debug("Json select list: " + listType);
        
        selectList = new ArrayList<String>();
        selectObjList = new ArrayList<ListValue>();
        selectMap = new HashMap<String, String>();

        return dispatchMethod(listType);
    }

    public String userlist() {
        int max = Integer.parseInt(getText("page.common.prename_max"));
        for (int i = 1; i <= max; i++) {
            String txt = getText("page.common.prename_" + i);
            selectList.add(txt);
            selectMap.put(String.valueOf(i), txt);
        }
        return SUCCESS;
    }

    public String paymethodMap() {
        int max = Integer.parseInt(getText("page.common.paymethod_max"));
        for (int i = 1; i <= max; i++) {
            String txt = getText("page.common.paymethod_" + i);
            selectList.add(txt);
            selectObjList.add(new ListValue(String.valueOf(i), txt));
            selectMap.put(String.valueOf(i), txt);
        }
        return SUCCESS;
    }

    @SuppressWarnings("unchecked")
    public String catalogList() {
        List<FrmCatalog> frmCatalogs = (List<FrmCatalog>) getManager().findAll();
        for (FrmCatalog form : frmCatalogs) {
            selectList.add(form.getName());
            selectMap.put(String.valueOf(form.getId()), form.getName());
            selectObjList.add(new ListValue(form.getId(), form.getName()));
        }
        return SUCCESS;
    }

    public List<String> getSelectList() {
        return selectList;
    }

    public void setSelectList(List<String> selectList) {
        this.selectList = selectList;
    }

    public List<ListValue> getSelectObjList() {
        return selectObjList;
    }

    public void setSelectObjList(List<ListValue> selectObjList) {
        this.selectObjList = selectObjList;
    }

    public Map<String, String> getSelectMap() {
        return selectMap;
    }

    public void setSelectMap(Map<String, String> selectMap) {
        this.selectMap = selectMap;
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

}