package org.ktm.actions.web;

import java.util.StringTokenizer;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.ktm.actions.KTMAction;
import org.ktm.web.form.FrmBeveragePackage;
import org.ktm.web.manager.BeveragePackageManager;
import org.ktm.web.manager.ServiceLocator;

@ParentPackage(value = "ktm-default")
public class CrudGridProductType extends KTMAction {

    private static final long serialVersionUID = -7492977888229483777L;
    private Logger            log              = Logger.getLogger(CrudGridProductType.class);

    private String            oper             = "";
    private String            id;
    private String            identifier;
    private String            name;
    private String            unitType;
    private Integer           unitCount;
    private String            catalogName;
    private double            price1;
    private double            price2;

    @Actions({ @Action(value = "/crud-grid-product-type", results = { @Result(location = "simpleecho.jsp", name = "success"), @Result(location = "simpleecho.jsp", name = "input") }) })
    public String execute() throws Exception {
        log.debug("id :" + id);
        log.debug("identifier :" + identifier);
        log.debug("name :" + name);
        log.debug("unitType:" + unitType);
        log.debug("unitCount: " + unitCount);
        log.debug("catalogName: " + catalogName);
        log.debug("price 1: " + price1);
        log.debug("price 2: " + price2);

        if (oper.equalsIgnoreCase("del")) {
            StringTokenizer ids = new StringTokenizer(id, ",");
            while (ids.hasMoreTokens()) {
                int removeId = Integer.parseInt(ids.nextToken());
                log.debug("Delete productType " + removeId);
                getManager().delete(removeId);
            }
        } else {
            FrmBeveragePackage bp = new FrmBeveragePackage();
            bp.setIdentifier(identifier);
            bp.setName(name);
            bp.setUnitType(unitType);
            bp.setUnitCount(unitCount);
            bp.setCatalogName(catalogName);
            bp.setPrice1(price1);
            bp.setPrice2(price2);

            if (oper.equalsIgnoreCase("add")) {
                log.debug("Add new ProductCatalog");
                bp.setNew(true);
            } else if (oper.equalsIgnoreCase("edit")) {
                log.debug("Edit ProductCatalog");
                bp.setId(Integer.parseInt(id));
                bp.setNew(false);
            }

            getManager().addOrUpdate(bp);
        }

        return SUCCESS;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public Integer getUnitCount() {
        return unitCount;
    }

    public void setUnitCount(Integer unitCount) {
        this.unitCount = unitCount;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public double getPrice1() {
        return price1;
    }

    public void setPrice1(double price1) {
        this.price1 = price1;
    }

    public double getPrice2() {
        return price2;
    }

    public void setPrice2(double price2) {
        this.price2 = price2;
    }

    @Override
    protected BeveragePackageManager getManager() {
        return ServiceLocator.getBeveragePackageManager();
    }

}
