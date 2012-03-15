package org.ktm.web.manager;

import java.util.Collection;
import org.ktm.domain.party.Person;
import org.ktm.web.form.FrmDomain;
import org.ktm.web.form.FrmPerson;

public interface PersonManager extends FormManager {

    public void syncFormPerson(Person person, FrmPerson form);
    
    public void syncFormPersonCollection(Collection<Person> persons, Collection<FrmDomain> myPersons);
}
