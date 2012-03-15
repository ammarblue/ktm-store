package org.ktm.web.manager;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.ktm.dao.KTMEMDaoFactory;
import org.ktm.dao.party.PartyRoleDao;
import org.ktm.dao.party.PersonDao;
import org.ktm.domain.party.Address;
import org.ktm.domain.party.AddressEType;
import org.ktm.domain.party.AddressProperties;
import org.ktm.domain.party.EmailAddress;
import org.ktm.domain.party.PartyIdentifier;
import org.ktm.domain.party.PartyRole;
import org.ktm.domain.party.Person;
import org.ktm.domain.party.RegisteredIdentifier;
import org.ktm.domain.party.TelephoneAddress;
import org.ktm.exception.CreateException;
import org.ktm.exception.DeleteException;
import org.ktm.web.form.FrmDomain;
import org.ktm.web.form.FrmPerson;
import org.ktm.web.utils.DateUtils;

public class PersonManagerImpl extends FrmManagerAbstractImpl implements PersonManager {
    
    private static PersonManagerImpl theInstance = null;
    
    public static PersonManagerImpl getInstance() {
        if (theInstance == null) {
            theInstance = new PersonManagerImpl();
        }
        return theInstance;
    }

    private PersonDao getDao() {
        return KTMEMDaoFactory.getInstance().getPersonDao();
    }
    
    @Override
    public synchronized FrmDomain get(Serializable tryId) {
        FrmPerson fperson = null;
        Person person = (Person) getDao().get(tryId);
        if (person != null) {
            fperson = new FrmPerson();
            syncFormPerson(person, fperson);
        }
        return fperson;
    }

    @Override
    @SuppressWarnings("unchecked")
    public synchronized List<FrmDomain> findAll() {
        List<FrmDomain> myPersons = new ArrayList<FrmDomain>();
        syncFormPersonCollection((Collection<Person>) getDao().findAll(), myPersons);
        return myPersons;
    }

    @Override
    public Integer delete(Serializable tryId) {
        try {
            return getDao().delete(tryId);
        } catch (DeleteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public synchronized Integer delete(FrmDomain toDelete) {
        return delete(toDelete.getId());
    }

    @Override
    public synchronized Integer addOrUpdate(FrmDomain toAdd) {
        FrmPerson frmPerson = (FrmPerson) toAdd;
        Person person = new Person();

        PersonDao personDao = KTMEMDaoFactory.getInstance().getPersonDao();

        if (toAdd.isNew()) {
            PartyIdentifier ident = new PartyIdentifier();
            ident.setIdentifier(frmPerson.getIdentifier());
            person.setIdentifier(ident);

            RegisteredIdentifier regid = new RegisteredIdentifier();
            regid.setIdentifier(frmPerson.getRegisteredIdentifier());
            regid.setParty(person);
            person.getRegisteredIdentifiers().add(regid);

            EmailAddress email = new EmailAddress();
            email.setEmail(frmPerson.getEmailAddress());
            TelephoneAddress phone = new TelephoneAddress();
            phone.setNumber(frmPerson.getTel());

            AddressProperties addrp = new AddressProperties();
            addrp.setAddress(email);
            addrp.setParty(person);
            email.getPartys().add(addrp);
            addrp.setUseage(AddressEType.EMAIL);
            person.getAddresses().add(addrp);

            addrp = new AddressProperties();
            addrp.setAddress(phone);
            addrp.setParty(person);
            email.getPartys().add(addrp);
            addrp.setUseage(AddressEType.TELEPHONE);
            person.getAddresses().add(addrp);

            PartyRole role = new PartyRole();
            role.setName("Employee");
            role.setParty(person);
            person.getRoles().add(role);
        } else {
            person = (Person) personDao.get(frmPerson.getId());
            PartyIdentifier ident = person.getIdentifier();
            if (ident == null) {
                ident = new PartyIdentifier();
                ident.setIdentifier(frmPerson.getIdentifier());
                person.setIdentifier(ident);
            } else {
                person.getIdentifier().setIdentifier(frmPerson.getIdentifier());
            }

            Iterator<RegisteredIdentifier> regids = person.getRegisteredIdentifiers().iterator();
            RegisteredIdentifier regid = null;
            if (regids.hasNext()) {
                regid = regids.next();
                regid.setIdentifier(frmPerson.getRegisteredIdentifier());
            } else {
                regid = new RegisteredIdentifier();
                regid.setIdentifier(frmPerson.getRegisteredIdentifier());
                regid.setParty(person);
                person.getRegisteredIdentifiers().add(regid);
            }

            boolean found = false;
            Iterator<AddressProperties> addrps = person.getAddresses().iterator();
            while (addrps.hasNext()) {
                AddressProperties addrp = addrps.next();
                if (AddressEType.EMAIL.equals(addrp.getUseage())) {
                    Address adds = addrp.getAddress();
                    if (adds instanceof EmailAddress) {
                        ((EmailAddress) adds).setEmail(frmPerson.getEmailAddress());
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                EmailAddress email = new EmailAddress();
                email.setEmail(frmPerson.getEmailAddress());
                AddressProperties addrp = new AddressProperties();
                addrp.setAddress(email);
                addrp.setParty(person);
                email.getPartys().add(addrp);
                addrp.setUseage(AddressEType.EMAIL);
                person.getAddresses().add(addrp);
            }
            found = false;
            addrps = person.getAddresses().iterator();
            while (addrps.hasNext()) {
                AddressProperties addrp = addrps.next();
                if (AddressEType.TELEPHONE.equals(addrp.getUseage())) {
                    Address adds = addrp.getAddress();
                    if (adds instanceof TelephoneAddress) {
                        ((TelephoneAddress) adds).setNumber(frmPerson.getTel());
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                TelephoneAddress phone = new TelephoneAddress();
                phone.setNumber(frmPerson.getTel());
                AddressProperties addrp = new AddressProperties();
                addrp.setAddress(phone);
                addrp.setParty(person);
                phone.getPartys().add(addrp);
                addrp.setUseage(AddressEType.TELEPHONE);
                person.getAddresses().add(addrp);
            }

            Set<PartyRole> roles = person.getRoles();
            PartyRoleDao partyRoleDao = KTMEMDaoFactory.getInstance().getPartyRoleDao();
            
            PartyRole role = partyRoleDao.findByRoleName(person, "Employee");
            if (role == null) {
                role = new PartyRole();
                role.setName("Employee");
                role.setParty(person);
                roles.add(role);
            }
        }

        person.setPrename(frmPerson.getPrename());
        person.setFirstname(frmPerson.getFirstname());
        person.setLastname(frmPerson.getLastname());
        person.setBirthDay(frmPerson.getBirthDay());

        Integer id = 0;
        try {
            id = (Integer) personDao.create(person);
        } catch (CreateException e) {
            e.printStackTrace();
        }
        
        return id;
    }

    public synchronized void syncFormPerson(Person person, FrmPerson form) {
        if (person != null && form != null) {
            if (person.getUniqueId() != null) {
                form.setId(person.getUniqueId());
            }
            if (person.getPrename() != null) {
                form.setPrename(person.getPrename());
            }
            if (person.getFirstname() != null) {
                form.setFirstname(person.getFirstname());
            }
            if (person.getLastname() != null) {
                form.setLastname(person.getLastname());
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

    public synchronized void syncFormPersonCollection(Collection<Person> persons, Collection<FrmDomain> myPersons) {
        if (persons != null && persons.size() > 0) {
            if (myPersons != null) {
                Iterator<Person> it1 = persons.iterator();
                Iterator<FrmDomain> it2 = myPersons.iterator();

                List<FrmPerson> tmps = new ArrayList<FrmPerson>();

                FrmPerson f = null;
                while (it1.hasNext()) {
                    Person p = it1.next();
                    if (!it2.hasNext()) {
                        f = new FrmPerson();
                        tmps.add(f);
                    } else {
                        FrmDomain obj = it2.next();
                        if (obj instanceof FrmPerson) {
                            f = (FrmPerson) obj;
                        }
                    }
                    this.syncFormPerson(p, f);
                }

                if (tmps.size() > 0) {
                    for (FrmPerson nf : tmps) {
                        myPersons.add(nf);
                    }
                }
            }
        }
    }

}
