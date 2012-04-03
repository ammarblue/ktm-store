package org.ktm.web.manager;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.ktm.dao.KTMEMDaoFactory;
import org.ktm.dao.party.EmploymentDao;
import org.ktm.dao.party.PersonDao;
import org.ktm.domain.party.Address;
import org.ktm.domain.party.AddressEType;
import org.ktm.domain.party.AddressProperties;
import org.ktm.domain.party.EmailAddress;
import org.ktm.domain.party.Employee;
import org.ktm.domain.party.Employer;
import org.ktm.domain.party.Employment;
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

public class EmploymentManagerImpl extends FrmManagerAbstractImpl  implements EmploymentManager {

    private static EmploymentManager theInstance = null;

    public static FormManager getInstance() {
        if (theInstance == null) {
            theInstance = new EmploymentManagerImpl();
        }
        return theInstance;
    }

    private EmploymentDao getDao() {
        return KTMEMDaoFactory.getInstance().getEmploymentDao();
    }
    
    @Override
    public FrmDomain get(Serializable tryId) {
        FrmPerson fperson = null;
        Employment emp = (Employment) getDao().get(tryId);
        if (emp != null) {
            PartyRole emr = emp.getSupply();
            PartyRole eme = emp.getClient();
            if (emr != null && eme != null) {
                fperson = new FrmPerson();
                // TODO: set Employer information
                if (emr instanceof Employer) {
                    
                }
                
                if (eme instanceof Employee) {
                    Person person = (Person) eme.getParty();
                    syncFormPerson(person, fperson);
                }
            }
        }
        return fperson;
    }

    @Override
    public List<?> findAll() {
        List<FrmDomain> myPersons = new ArrayList<FrmDomain>();
        Collection<?> objs = getDao().findAll();
        for (Object obj : objs) {
            if (obj instanceof Employment) {
                Employment emm = (Employment) obj;
                
                //PartyRole emr = emm.getSupply();
                // TODO: Employer information

                PartyRole eme = emm.getClient();
                if(eme instanceof Employee) {
                    FrmPerson fperson = new FrmPerson();
                    Person person = (Person) eme.getParty();
                    syncFormPerson(person, fperson);
                    myPersons.add(fperson);
                }
            }
        }
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
    public Integer addOrUpdate(FrmDomain toAdd) {
        FrmPerson frmPerson = (FrmPerson) toAdd;
        Employment emm = null;
        Employee eme = null;
        Person person = null;

        PersonDao personDao = KTMEMDaoFactory.getInstance().getPersonDao();

        if (toAdd.isNew()) {
            // TODO: add Employer information
            
            emm = new Employment();
            
            eme = new Employee();
            person = new Person();
            
            eme.setName("Employee");
            eme.setParty(person);
            person.getRoles().add(eme);
            
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
            addrp.setUseage(AddressEType.EMAIL);
            person.getAddresses().add(addrp);

            addrp = new AddressProperties();
            addrp.setAddress(phone);
            addrp.setParty(person);
            addrp.setUseage(AddressEType.TELEPHONE);
            person.getAddresses().add(addrp);
        } else {
            emm = (Employment) getDao().get(frmPerson.getId());
            eme = (Employee) emm.getClient();
            
            // TODO: employer information
            
            person = (Person) eme.getParty();
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
                addrp.setUseage(AddressEType.TELEPHONE);
                person.getAddresses().add(addrp);
            }
        }

        person.setPrename(frmPerson.getPrename());
        person.setFirstname(frmPerson.getFirstname());
        person.setLastname(frmPerson.getLastname());
        person.setBirthDay(frmPerson.getBirthDay());

        Integer id = 0;
        try {
            Integer personId = (Integer) personDao.create(person);
            person = (Person) personDao.get(personId);
            
            Set<PartyRole> roles = person.getRoles();
            for (PartyRole role : roles) {
                if (role instanceof Employee) {
                    eme = (Employee) role;
                    break;
                }
            }
            emm.setClient(eme);
            
            id = (Integer) getDao().create(emm);
            
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
}
