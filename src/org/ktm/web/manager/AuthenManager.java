package org.ktm.web.manager;

import org.ktm.domain.party.Authen;
import org.ktm.domain.party.Person;
import org.ktm.web.form.FrmAuthen;

public interface AuthenManager extends FormManager {

    public void syncFormAuthen(Authen authen, FrmAuthen form);

    public void syncFormPerson(Person person, FrmAuthen form);
    
}
