package org.ktm.web.manager;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.ktm.dao.KTMEMDaoFactory;
import org.ktm.dao.party.CustomerDao;
import org.ktm.dao.party.PartyRoleDao;
import org.ktm.domain.party.Address;
import org.ktm.domain.party.AddressEType;
import org.ktm.domain.party.AddressProperties;
import org.ktm.domain.party.Customer;
import org.ktm.domain.party.EmailAddress;
import org.ktm.domain.party.PartyRole;
import org.ktm.domain.party.PartyRoleIdentifier;
import org.ktm.domain.party.Person;
import org.ktm.domain.party.RegisteredIdentifier;
import org.ktm.domain.party.TelephoneAddress;
import org.ktm.exception.CreateException;
import org.ktm.exception.DeleteException;
import org.ktm.web.form.FrmCustomer;
import org.ktm.web.form.FrmDomain;
import org.ktm.web.utils.DateUtils;

public class CustomerManagerImpl extends FrmManagerAbstractImpl implements CustomerManager {

    private static CustomerManager theInstance = null;
    
    public static CustomerManager getInstance() {
        if (theInstance == null) {
            theInstance = new CustomerManagerImpl();
        }
        return theInstance;
    }
    
    private CustomerDao getDao() {
        return KTMEMDaoFactory.getInstance().getCustomerDao();
    }
    
    @Override
    public FrmDomain get(Serializable tryId) {
        FrmCustomer form = new FrmCustomer();
        Customer cus = (Customer) getDao().get(tryId);
        if (cus != null) {
            syncFormCustomer(cus, form);
        }
        return form;
    }

    @Override
    public List<?> findAll() {
        List<FrmDomain> myCustomers = new ArrayList<FrmDomain>();
        Collection<?> customers = getDao().findAll();
        for (Object obj : customers) {
            if (obj instanceof Customer) {
                FrmCustomer form = new FrmCustomer();
                syncFormCustomer((Customer)obj, form);
                myCustomers.add(form);
            }
        }
        return myCustomers;
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
        FrmCustomer frmCustomer = (FrmCustomer) toAdd;
        Customer cus = new Customer();
        Person person = null;

        CustomerDao dao = KTMEMDaoFactory.getInstance().getCustomerDao();

        if (toAdd.isNew()) {
            PartyRoleIdentifier ident = new PartyRoleIdentifier();
            ident.setIdentifier(frmCustomer.getIdentifier());
            cus.setIdentifier(ident);
            cus.setDescription(frmCustomer.getDesc());

            person = new Person();
            
            RegisteredIdentifier regid = new RegisteredIdentifier();
            regid.setIdentifier(frmCustomer.getRegisteredIdentifier());
            regid.setParty(person);
            person.getRegisteredIdentifiers().add(regid);

            EmailAddress email = new EmailAddress();
            email.setEmail(frmCustomer.getEmailAddress());
            TelephoneAddress phone = new TelephoneAddress();
            phone.setNumber(frmCustomer.getTel());

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

            PartyRole role = new PartyRole();
            role.setName("Employee");
            role.setParty(person);
            person.getRoles().add(role);
        } else {
            cus = (Customer) dao.get(frmCustomer.getId());
            person = (Person) cus.getParty();
            if (person == null) {
                person = new Person();
                person.getRoles().add(cus);
            }
            PartyRoleIdentifier ident = cus.getIdentifier();
            if (ident == null) {
                ident = new PartyRoleIdentifier();
                ident.setIdentifier(frmCustomer.getIdentifier());
                cus.setIdentifier(ident);
            } else {
                cus.getIdentifier().setIdentifier(frmCustomer.getIdentifier());
            }

            Iterator<RegisteredIdentifier> regids = person.getRegisteredIdentifiers().iterator();
            RegisteredIdentifier regid = null;
            if (regids.hasNext()) {
                regid = regids.next();
                regid.setIdentifier(frmCustomer.getRegisteredIdentifier());
            } else {
                regid = new RegisteredIdentifier();
                regid.setIdentifier(frmCustomer.getRegisteredIdentifier());
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
                        ((EmailAddress) adds).setEmail(frmCustomer.getEmailAddress());
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                EmailAddress email = new EmailAddress();
                email.setEmail(frmCustomer.getEmailAddress());
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
                        ((TelephoneAddress) adds).setNumber(frmCustomer.getTel());
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                TelephoneAddress phone = new TelephoneAddress();
                phone.setNumber(frmCustomer.getTel());
                AddressProperties addrp = new AddressProperties();
                addrp.setAddress(phone);
                addrp.setParty(person);
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

        person.setPrename(frmCustomer.getPrename());
        person.setFirstname(frmCustomer.getFirstname());
        person.setLastname(frmCustomer.getLastname());
        person.setBirthDay(frmCustomer.getBirthDay());

        Integer id = 0;
        try {
            id = (Integer) dao.create(cus);
        } catch (CreateException e) {
            e.printStackTrace();
        }
        
        return id;
    }

    public synchronized void syncFormCustomer(Customer customer, FrmCustomer form) {
        if (customer != null && form != null) {
            if (customer.getUniqueId() != null) {
                form.setId(customer.getUniqueId());
            }
            if (customer.getDescription() != null) {
                form.setDesc(customer.getDescription());
            }
            Person person = (Person) customer.getParty();
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
