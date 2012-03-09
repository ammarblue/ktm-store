package org.ktm.actions;

import java.util.ArrayList;
import java.util.Collection;
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
import org.ktm.dao.party.PersonDao;
import org.ktm.domain.party.Person;
import org.ktm.web.form.FrmPerson;

@ParentPackage(value = "ktm-default")
public class JsonPerson extends JsonAbstractAction {

    private static final long serialVersionUID = 8072293334749008043L;
    private Logger log = Logger.getLogger(JsonPerson.class);

    private List<FrmPerson> gridModel;

    private PersonDao personDao;

    @Actions({ @Action(value = "/jsonperson", results = { @Result(name = "success", type = "json"), @Result(name = INPUT, location = "user-login", type = "tiles") }) })
    @SuppressWarnings("unchecked")
    public String execute() {
        log.debug("Page " + getPage() + " Rows " + getRows() + " Sorting Order " + getSord() + " Index Row :" + getSidx());
        log.debug("Search :" + searchField + " " + searchOper + " " + searchString);

        log.debug("Get person List");
        List<FrmPerson> myPersons = new ArrayList<FrmPerson>();
        syncFormPersonCollection((Collection<Person>) getDao().findAll(), myPersons);

        if (sord != null && sord.equalsIgnoreCase("asc")) {
            Collections.sort(myPersons);
        }
        if (sord != null && sord.equalsIgnoreCase("desc")) {
            Collections.sort(myPersons);
            Collections.reverse(myPersons);
        }

        // Count all record (select count(*) from your_custumers)
        records = myPersons.size();

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
                setGridModel(myPersons.subList(0, totalrows));
            } else {
                // All Custumer
                setGridModel(myPersons);
            }
        } else {
            // Search Custumers
            if (searchString != null && searchOper != null) {
                int id = Integer.parseInt(searchString);
                if (searchOper.equalsIgnoreCase("eq")) {
                    log.debug("search id equals " + id);
                    List<FrmPerson> cList = new ArrayList<FrmPerson>();
                    Person person = (Person) getDao().get(id);
                    FrmPerson fperson = new FrmPerson();

                    syncFormPerson(person, fperson);

                    if (person != null) {
                        cList.add(fperson);
                    }

                    setGridModel(cList);
                } else if (searchOper.equalsIgnoreCase("ne")) {
                    log.debug("search id not " + id);
                    setGridModel((List<FrmPerson>) ((PersonDao) getDao()).findNotById(myPersons, id, from, to));
                } else if (searchOper.equalsIgnoreCase("lt")) {
                    log.debug("search id lesser then " + id);
                    setGridModel((List<FrmPerson>) ((PersonDao) getDao()).findLesserAsId(myPersons, id, from, to));
                } else if (searchOper.equalsIgnoreCase("gt")) {
                    log.debug("search id greater then " + id);
                    setGridModel((List<FrmPerson>) ((PersonDao) getDao()).findGreaterAsId(myPersons, id, from, to));
                }
            } else {
                setGridModel((List<FrmPerson>) ((PersonDao) getDao()).getSubList(myPersons, from, to));
            }
        }

        // Calculate total Pages
        total = (int) Math.ceil((double) records / (double) rows);

        return SUCCESS;
    }

    public String getJSON() {
        return execute();
    }

    public List<FrmPerson> getGridModel() {
        return gridModel;
    }

    public void setGridModel(List<FrmPerson> gridModel) {
        this.gridModel = gridModel;
    }

    @Override
    protected Dao getDao() {
        if (personDao == null) {
            personDao = KTMEMDaoFactory.getInstance().getPersonDao(this);
        }
        return personDao;
    }

}
