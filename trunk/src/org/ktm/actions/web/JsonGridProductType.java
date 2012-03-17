package org.ktm.actions.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.ktm.actions.json.JsonAbstractAction;
import org.ktm.web.form.FrmBeveragePackage;
import org.ktm.web.form.FrmCatalog;
import org.ktm.web.manager.BeveragePackageManager;
import org.ktm.web.manager.ServiceLocator;

@ParentPackage(value = "ktm-default")
public class JsonGridProductType extends JsonAbstractAction {

    private static final long        serialVersionUID = 1145674274087102711L;
    private Logger                   log              = Logger.getLogger(JsonGridProductType.class);

    // Your result List
    private List<FrmBeveragePackage> gridModel;

    private List<FrmBeveragePackage> myProductTypes;

    private List<FrmCatalog>         catalogs;

    @Override
    protected BeveragePackageManager getManager() {
        return ServiceLocator.getBeveragePackageManager();
    }

    @SuppressWarnings("unchecked")
    @Actions({ @Action(value = "/json-grid-product-type", results = { @Result(name = "success", type = "json") }) })
    public String execute() {
        log.debug("Page " + getPage() + " Rows " + getRows() + " Sorting Order " + getSord() + " Index Row :" + getSidx());
        log.debug("Search :" + searchField + " " + searchOper + " " + searchString);

        log.debug("Build new List");
        myProductTypes = (List<FrmBeveragePackage>) getManager().findAll();

        if (sord != null && sord.equalsIgnoreCase("asc")) {
            Collections.sort(myProductTypes);
        }
        if (sord != null && sord.equalsIgnoreCase("desc")) {
            Collections.sort(myProductTypes);
            Collections.reverse(myProductTypes);
        }

        // Count all record (select count(*) from your_custumers)
        records = getManager().getProductCount(myProductTypes);

        if (totalrows != null) {
            records = totalrows;
        }

        // Calucalate until rows ware selected
        int to = (rows * page);

        // Calculate the first row to read
        int from = to - rows;

        // Set to = max rows
        if (to > records) to = records;

        if (loadonce) {
            if (totalrows != null && totalrows > 0) {
                // setGridModel(myProductTypes.subList(0, totalrows));
                setGridModel(myProductTypes);
            } else {
                // All Custumer
                setGridModel(myProductTypes);
            }
        } else {
            // Search Custumers
            if (searchString != null && searchOper != null) {
                int id = Integer.parseInt(searchString);
                if (searchOper.equalsIgnoreCase("eq")) {
                    log.debug("search id equals " + id);
                    List<FrmBeveragePackage> cList = new ArrayList<FrmBeveragePackage>();
                    FrmBeveragePackage bp = getManager().findById(myProductTypes, id);

                    if (bp != null) cList.add(bp);

                    setGridModel(cList);
                } else if (searchOper.equalsIgnoreCase("ne")) {
                    log.debug("search id not " + id);
                    setGridModel((List<FrmBeveragePackage>) getManager().findNotById(myProductTypes, id, from, to));
                } else if (searchOper.equalsIgnoreCase("lt")) {
                    log.debug("search id lesser then " + id);
                    setGridModel((List<FrmBeveragePackage>) getManager().findLesserAsId(myProductTypes, id, from, to));
                } else if (searchOper.equalsIgnoreCase("gt")) {
                    log.debug("search id greater then " + id);
                    setGridModel((List<FrmBeveragePackage>) getManager().findGreaterAsId(myProductTypes, id, from, to));
                }
            } else {
                // setGridModel((List<FrmBeveragePackage>)
                // getManager().getSubList(myProductTypes, from, to));
                setGridModel(myProductTypes);
            }
        }

        // Calculate total Pages
        total = (int) Math.ceil((double) records / (double) rows);

        return SUCCESS;
    }

    public String getJSON() {
        return execute();
    }

    /**
     * @return how many rows we want to have into the grid
     */
    public Integer getRows() {
        return rows;
    }

    /**
     * @param rows
     *            how many rows we want to have into the grid
     */
    public void setRows(Integer rows) {
        this.rows = rows;
    }

    /**
     * @return current page of the query
     */
    public Integer getPage() {
        return page;
    }

    /**
     * @param page
     *            current page of the query
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * @return total pages for the query
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * @param total
     *            total pages for the query
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * @return total number of records for the query. e.g. select count(*) from
     *         table
     */
    public Integer getRecords() {
        return records;
    }

    /**
     * @param record
     *            total number of records for the query. e.g. select count(*)
     *            from table
     */
    public void setRecords(Integer records) {

        this.records = records;

        if (this.records > 0 && this.rows > 0) {
            this.total = (int) Math.ceil((double) this.records / (double) this.rows);
        } else {
            this.total = 0;
        }
    }

    /**
     * @return an collection that contains the actual data
     */
    public List<FrmBeveragePackage> getGridModel() {
        return gridModel;
    }

    /**
     * @param gridModel
     *            an collection that contains the actual data
     */
    public void setGridModel(List<FrmBeveragePackage> gridModel) {
        this.gridModel = gridModel;
    }

    /**
     * @return sorting order
     */
    public String getSord() {
        return sord;
    }

    /**
     * @param sord
     *            sorting order
     */
    public void setSord(String sord) {
        this.sord = sord;
    }

    /**
     * @return get index row - i.e. user click to sort.
     */
    public String getSidx() {
        return sidx;
    }

    /**
     * @param sidx
     *            get index row - i.e. user click to sort.
     */
    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public void setSearchOper(String searchOper) {
        this.searchOper = searchOper;
    }

    public void setLoadonce(boolean loadonce) {
        this.loadonce = loadonce;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public void setTotalrows(Integer totalrows) {
        this.totalrows = totalrows;
    }

    public List<FrmCatalog> getCatalogs() {
        return catalogs;
    }

    public void setCatalogs(List<FrmCatalog> catalogs) {
        this.catalogs = catalogs;
    }

}
