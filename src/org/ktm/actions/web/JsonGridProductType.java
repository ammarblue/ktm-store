package org.ktm.actions.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.ktm.actions.json.JsonAbstractAction;
import org.ktm.web.form.FrmBeveragePackage;
import org.ktm.web.manager.ProductCatalogManager;
import org.ktm.web.manager.ServiceLocator;

@ParentPackage(value = "ktm-default")
public class JsonGridProductType extends JsonAbstractAction {

    private static final long    serialVersionUID = 1145674274087102711L;
    private Logger               log              = Logger.getLogger(JsonGridProductType.class);

    // Your result List
    private List<FrmBeveragePackage> gridModel;

    // for Footerrow
    private Map<String, Object>  userdata         = new HashMap<String, Object>();

    @SuppressWarnings("unchecked")
    @Actions({ @Action(value = "/json-grid-product-type", results = { @Result(name = "success", type = "json") }) })
    public String execute() {
        log.debug("Details for Product Type : " + id);

        // Calcalate until rows ware selected
        int to = (rows * page);

        // Criteria to Build SQL
        if (id != null) {
            gridModel = (List<FrmBeveragePackage>) getManager().findProductType();
        }

        /*
         * Double priceeach = 0.0; for (Orderdetails od : gridModel) { priceeach
         * += od.getPriceeach(); } userdata.put("priceeach", priceeach);
         * userdata.put("productname", "Total :");
         */

        records = gridModel.size();

        // Set to = max rows
        if (to > records) to = records;

        // Calculate total Pages
        total = (int) Math.ceil((double) records / (double) rows);

        return SUCCESS;
    }

    public String getJSON() {
        return execute();
    }

    public List<FrmBeveragePackage> getGridModel() {
        return gridModel;
    }

    @Override
    protected ProductCatalogManager getManager() {
        return ServiceLocator.getProductCatalogManager();
    }

    public Map<String, Object> getUserdata() {
        return userdata;
    }

}
