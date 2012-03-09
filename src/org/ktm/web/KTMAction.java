package org.ktm.web;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.ktm.domain.party.*;
import org.ktm.web.form.*;
import org.ktm.web.utils.DateUtils;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public abstract class KTMAction extends AbstractCRUDAction {

    private static final long serialVersionUID = 4025001861137214635L;

    public static final String SUCCESS = "success";

    private boolean isOwnerRead = false;

    @SessionTarget
    protected Session hbmSession;

    @TransactionTarget
    protected Transaction transaction;

    public void lock() {
        isOwnerRead = true;
    }

    public void unlock() {
        isOwnerRead = false;
    }

    public boolean isOwner() {
        return isOwnerRead;
    }

    protected boolean isEmpty(String str) {
        if (str != null) {
            return str.isEmpty();
        }
        return true;
    }

    public Session getKTMSession() {
        if (isOwner()) {
            return hbmSession;
        }
        return null;
    }

    public Transaction getKTMTransatcion() {
        if (isOwner()) {
            return transaction;
        }
        return null;
    }

    public void syncFormPerson(Person person, FrmPerson form) {
        if (person != null && form != null) {
            if (person.getUniqueId() != null) {
                form.setUniqueId(person.getUniqueId());
            }
            if (person.getPreName() != null) {
                form.setPreName(person.getPreName());
            }
            if (person.getFirstName() != null) {
                form.setFirstName(person.getFirstName());
            }
            if (person.getLastName() != null) {
                form.setLastName(person.getLastName());
            }
            if (person.getIdentifier() != null) {
                form.setIdentifier(person.getIdentifier().getIdentifier());
            }
            if (person.getBirthDay() != null) {
                try {
                    form.setBirthDay(DateUtils.formatDate(person.getBirthDay()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (person.getRegisteredIdentifiers() != null && person.getRegisteredIdentifiers().size() > 0) {
                Iterator<RegisteredIdentifier> regids = person.getRegisteredIdentifiers().iterator();
                if (regids.hasNext()) {
                    RegisteredIdentifier regid = regids.next();
                    form.setRegisteredIdentifier(regid.getIdentifier());
                }
            }
            if (person.getAddresses() != null) {
                Set<AddressProperties> addps = person.getAddresses();
                for (AddressProperties addrp : addps) {
                    if (AddressEType.EMAIL.equals(addrp.getUseage())) {
                        form.setEmailAddress(((EmailAddress) addrp.getAddress()).getEmail());
                    }
                    if (AddressEType.TELEPHONE.equals(addrp.getUseage())) {
                        form.setTel(((TelephoneAddress) addrp.getAddress()).getNumber());
                    }
                }
            }
        }
    }

    public void syncFormPersonCollection(Collection<Person> persons, Collection<FrmPerson> forms) {
        if (persons != null && persons.size() > 0) {
            if (forms != null) {
                Iterator<Person> it1 = persons.iterator();
                Iterator<FrmPerson> it2 = forms.iterator();

                List<FrmPerson> tmps = new ArrayList<FrmPerson>();

                FrmPerson f;
                while (it1.hasNext()) {
                    Person p = it1.next();
                    if (!it2.hasNext()) {
                        f = new FrmPerson();
                        tmps.add(f);
                    } else {
                        f = it2.next();
                    }
                    this.syncFormPerson(p, f);
                }

                if (tmps.size() > 0) {
                    for (FrmPerson nf : tmps) {
                        forms.add(nf);
                    }
                }
            }
        }
    }

    protected void syncFormAuthen(Authen authen, FrmAuthen form) {
        if (authen != null && form != null) {
            if (authen.getUniqueId() != null) {
                form.setUniqueId(authen.getUniqueId());
            }
        }
        if (authen.getParty() != null) {
            if (authen.getParty() instanceof Person) {
                Person person = (Person) authen.getParty();
                if (person.getPreName() != null) {
                    form.setPreName(person.getPreName());
                }
                if (person.getFirstName() != null) {
                    form.setFirstName(person.getFirstName());
                }
                if (person.getLastName() != null) {
                    form.setLastName(person.getLastName());
                }
            }
            if (authen.getUsername() != null) {
                form.setUsername(authen.getUsername());
            }
            form.setPassword("****");
            form.setConfirm("");
        }
    }
}
