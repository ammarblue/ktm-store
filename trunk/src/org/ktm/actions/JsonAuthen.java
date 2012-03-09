package org.ktm.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.ktm.actions.json.JsonAbstractAction;
import org.ktm.dao.Dao;
import org.ktm.dao.KTMEMDaoFactory;
import org.ktm.dao.party.*;
import org.ktm.domain.party.*;
import org.ktm.web.form.FrmAuthen;

@ParentPackage(value = "ktm-default")
public class JsonAuthen extends JsonAbstractAction {

    private static final long serialVersionUID = 8072293334749008043L;
    private Logger log = Logger.getLogger(JsonAuthen.class);

    private List<FrmAuthen> gridModel;

    private PersonDao personDao;
    private AuthenDao authenDao;

    @Actions({ @Action(value = "/jsonauthen", results = { @Result(name = "success", type = "json") }) })
    @SuppressWarnings("unchecked")
    public String execute() {
        log.debug("Page " + getPage() + " Rows " + getRows() + " Sorting Order " + getSord() + " Index Row :" + getSidx());
        log.debug("Search :" + searchField + " " + searchOper + " " + searchString);

        log.debug("Get authen List");
        if (authenDao == null) {
            authenDao = (AuthenDao) getDao();
        }

        List<FrmAuthen> myAuthens = new ArrayList<FrmAuthen>();
        List<Person> persons = (List<Person>) personDao.findAll();

        for (Person f : persons) {
            FrmAuthen frm = new FrmAuthen();
            frm.setUniqueId(f.getUniqueId());
            frm.setPreName(f.getPreName());
            frm.setFirstName(f.getFirstName());
            frm.setLastName(f.getLastName());

            List<Authen> authens = authenDao.findByPartyId(f.getUniqueId());
            if (authens != null && authens.size() > 0) {
                for (Authen authen : authens) {
                    frm.setUsername(authen.getUsername());
                    frm.setPassword("****");
                    frm.setConfirm("");
                    break;
                }
            }
            myAuthens.add(frm);
        }

        if (sord != null && sord.equalsIgnoreCase("asc")) {
            Collections.sort(myAuthens);
        }
        if (sord != null && sord.equalsIgnoreCase("desc")) {
            Collections.sort(myAuthens);
            Collections.reverse(myAuthens);
        }

        // Count all record (select count(*) from your_custumers)
        records = myAuthens.size();

        if (totalrows != null) {
            records = totalrows;
        }

        // Calucalate until rows ware selected
        int to = (rows * page);

        // Calculate the first row to read
        int from = to - rows;

        // Set to = max rows
        if (to > records) {
            to = records;
        }

        if (loadonce) {
            if (totalrows != null && totalrows > 0) {
                setGridModel(myAuthens.subList(0, totalrows));
            } else {
                // All Custumer
                setGridModel(myAuthens);
            }
        } else {
            // Search Custumers
            if (searchString != null && searchOper != null) {
                int id = Integer.parseInt(searchString);
                if (searchOper.equalsIgnoreCase("eq")) {
                    log.debug("search id equals " + id);
                    List<FrmAuthen> cList = new ArrayList<FrmAuthen>();
                    Person person = (Person) getDao().get(id);
                    FrmAuthen frm = new FrmAuthen();

                    List<Authen> authens = authenDao.findByPartyId(person.getUniqueId());
                    if (authens != null && authens.size() > 0) {
                        frm.setUniqueId(person.getUniqueId());
                        frm.setPreName(person.getPreName());
                        frm.setFirstName(person.getFirstName());
                        frm.setLastName(person.getLastName());

                        authens = authenDao.findByPartyId(person.getUniqueId());
                        if (authens != null && authens.size() > 0) {
                            for (Authen a : authens) {
                                frm.setUsername(a.getUsername());
                                frm.setPassword("****");
                                frm.setConfirm("");
                                break;
                            }
                        }
                    }

                    if (person != null) {
                        cList.add(frm);
                    }

                    setGridModel(cList);
                } else if (searchOper.equalsIgnoreCase("ne")) {
                    log.debug("search id not " + id);
                    setGridModel((List<FrmAuthen>) ((PersonDao) getDao()).findNotById(myAuthens, id, from, to));
                } else if (searchOper.equalsIgnoreCase("lt")) {
                    log.debug("search id lesser then " + id);
                    setGridModel((List<FrmAuthen>) ((PersonDao) getDao()).findLesserAsId(myAuthens, id, from, to));
                } else if (searchOper.equalsIgnoreCase("gt")) {
                    log.debug("search id greater then " + id);
                    setGridModel((List<FrmAuthen>) ((PersonDao) getDao()).findGreaterAsId(myAuthens, id, from, to));
                }
            } else {
                // setGridModel((List<FrmAuthen>) ((PersonDao)
                // getDao()).getSubList(myAuthens, from, to));
                setGridModel(myAuthens);
            }
        }

        // Calculate total Pages
        total = (int) Math.ceil((double) records / (double) rows);

        return SUCCESS;
    }

    public String getJSON() {
        return execute();
    }

    public List<FrmAuthen> getGridModel() {
        return gridModel;
    }

    public void setGridModel(List<FrmAuthen> gridModel) {
        this.gridModel = gridModel;
    }

    @Override
    protected Dao getDao() {
        if (authenDao == null) {
            authenDao = KTMEMDaoFactory.getInstance().getAuthenDao(this);
        }
        if (personDao == null) {
            personDao = KTMEMDaoFactory.getInstance().getPersonDao(this);
        }
        return authenDao;
    }

}
