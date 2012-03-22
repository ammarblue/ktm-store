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
import org.ktm.web.form.FrmCatalog;
import org.ktm.web.manager.ProductCatalogManager;
import org.ktm.web.manager.ServiceLocator;

public class JsonSelectCatalog extends JsonGridFieldsAction {

    private static final long    serialVersionUID = 1L;
    private static final Logger  log              = Logger.getLogger(JsonSelectCatalog.class);

    private List<String>         catalogList;
    private List<FrmCatalog>     catalogObjList;
    private Map<Integer, String> catalogMap;
    private List<String>         reloadList;

    // private String language;

    @SuppressWarnings("unchecked")
    @Actions({ @Action(value = "/json-select-catalog", results = { @Result(name = "success", type = "json") }) })
    public String execute() {
        log.debug("Get Json Catalog");

        initContext();
        
        catalogList = new ArrayList<String>();
        catalogObjList = new ArrayList<FrmCatalog>();
        catalogMap = new HashMap<Integer, String>();
        reloadList = new ArrayList<String>();

        List<FrmCatalog> frmCatalogs = (List<FrmCatalog>) getManager().findAll();
        for (FrmCatalog form : frmCatalogs) {
            catalogList.add(form.getName());
            catalogMap.put(form.getId(), form.getName());
            catalogObjList.add(form);
        }

        reloadList = new ArrayList<String>();

        /*
         * if (language != null && language.equalsIgnoreCase("J")) {
         * reloadList.add("Struts2"); reloadList.add("MyFaces");
         * reloadList.add("Tapestry"); } else if (language != null &&
         * language.equalsIgnoreCase("P")) { reloadList.add("CakePHP");
         * reloadList.add("Symfony"); reloadList.add("Zend"); } else if
         * (language != null && language.equalsIgnoreCase("C")) {
         * reloadList.add("NStruts"); reloadList.add("ProMesh.NET");
         * reloadList.add("Websharp"); }
         */

        return SUCCESS;
    }

    public String getJSON() {
        return execute();
    }

    public List<String> getCatalogList() {
        return catalogList;
    }

    public void setCatalogList(List<String> catalogList) {
        this.catalogList = catalogList;
    }

    public List<FrmCatalog> getCatalogObjList() {
        return catalogObjList;
    }

    public void setCatalogObjList(List<FrmCatalog> catalogObjList) {
        this.catalogObjList = catalogObjList;
    }

    public Map<Integer, String> getCatalogMap() {
        return catalogMap;
    }

    public void setCatalogMap(Map<Integer, String> catalogMap) {
        this.catalogMap = catalogMap;
    }

    public List<String> getReloadList() {
        return reloadList;
    }

    public void setReloadList(List<String> reloadList) {
        this.reloadList = reloadList;
    }

    @Override
    protected ProductCatalogManager getManager() {
        return ServiceLocator.getProductCatalogManager();
    }

}
