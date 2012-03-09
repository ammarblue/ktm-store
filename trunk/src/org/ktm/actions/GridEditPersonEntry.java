/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.ktm.actions;

import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.ktm.dao.Dao;
import org.ktm.dao.KTMEMDaoFactory;
import org.ktm.dao.party.PartyRoleDao;
import org.ktm.dao.party.PersonDao;
import org.ktm.domain.party.*;
import org.ktm.web.KTMAction;

@ParentPackage(value = "ktm-default")
public class GridEditPersonEntry extends KTMAction {

    private static final long serialVersionUID = -3454448309088641394L;
    private Logger log = Logger.getLogger(GridEditPersonEntry.class);

    private String oper = "";
    private String id;
    private String identifier;
    private String registeredIdentifier;
    private String preName;
    private String firstName;
    private String lastName;
    private String birthDay;
    private String emailAddress;
    private String tel;
    private PersonDao personDao;

    @Actions({ @Action(value = "/grid-edit-person-entry", results = { @Result(location = "simpleecho.jsp", name = "success"), @Result(location = "simpleecho.jsp", name = "input") }) })
    public String execute() throws Exception {
        log.debug("id :" + id);
        log.debug("identifier :" + identifier);
        log.debug("preName :" + preName);
        log.debug("firstName :" + firstName);
        log.debug("lastName :" + lastName);
        log.debug("birthDay :" + birthDay);
        log.debug("emailAddress:" + emailAddress);
        log.debug("tel:" + tel);

        Person person = null;

        if (oper.equalsIgnoreCase("del")) {
            StringTokenizer ids = new StringTokenizer(id, ",");
            while (ids.hasMoreTokens()) {
                int removeId = Integer.parseInt(ids.nextToken());
                log.debug("Delete Person " + removeId);
                getDao().delete(removeId);
            }
        } else {
            if (oper.equalsIgnoreCase("add")) {
                log.debug("Add Person");
                person = new Person();

                PartyIdentifier ident = new PartyIdentifier();
                ident.setIdentifier(identifier);
                person.setIdentifier(ident);

                RegisteredIdentifier regid = new RegisteredIdentifier();
                regid.setIdentifier(registeredIdentifier);
                regid.setParty(person);
                person.getRegisteredIdentifiers().add(regid);

                EmailAddress email = new EmailAddress();
                email.setEmail(emailAddress);
                TelephoneAddress phone = new TelephoneAddress();
                phone.setNumber(tel);

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
            } else if (oper.equalsIgnoreCase("edit")) {
                log.debug("Edit Person");

                person = (Person) getDao().get(Integer.parseInt(id));
                PartyIdentifier ident = person.getIdentifier();
                if (ident == null) {
                    ident = new PartyIdentifier();
                    ident.setIdentifier(identifier);
                    person.setIdentifier(ident);
                } else {
                    person.getIdentifier().setIdentifier(identifier);
                }

                Iterator<RegisteredIdentifier> regids = person.getRegisteredIdentifiers().iterator();
                RegisteredIdentifier regid = null;
                if (regids.hasNext()) {
                    regid = regids.next();
                    regid.setIdentifier(registeredIdentifier);
                } else {
                    regid = new RegisteredIdentifier();
                    regid.setIdentifier(registeredIdentifier);
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
                            ((EmailAddress) adds).setEmail(emailAddress);
                            found = true;
                            break;
                        }
                    }
                }
                if (!found) {
                    EmailAddress email = new EmailAddress();
                    email.setEmail(emailAddress);
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
                            ((TelephoneAddress) adds).setNumber(tel);
                            found = true;
                            break;
                        }
                    }
                }
                if (!found) {
                    TelephoneAddress phone = new TelephoneAddress();
                    phone.setNumber(tel);
                    AddressProperties addrp = new AddressProperties();
                    addrp.setAddress(phone);
                    addrp.setParty(person);
                    phone.getPartys().add(addrp);
                    addrp.setUseage(AddressEType.TELEPHONE);
                    person.getAddresses().add(addrp);
                }

                Set<PartyRole> roles = person.getRoles();
                PartyRoleDao partyRoleDao = KTMEMDaoFactory.getInstance().getPartyRoleDao(this);
                
                PartyRole role = partyRoleDao.findByRoleName(person, "Employee");
                if (role == null) {
                    role = new PartyRole();
                    role.setName("Employee");
                    role.setParty(person);
                    roles.add(role);
                }
            }

            person.setPreName(preName);
            person.setFirstName(firstName);
            person.setLastName(lastName);
            person.setBirthDay(birthDay);

            getDao().create(person);
        }
        return SUCCESS;
    }

    @Override
    protected Dao getDao() {
        if (personDao == null) {
            personDao = KTMEMDaoFactory.getInstance().getPersonDao(this);
        }
        return personDao;
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

    public String getPreName() {
        return preName;
    }

    public void setPreName(String preName) {
        this.preName = preName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRegisteredIdentifier() {
        return registeredIdentifier;
    }

    public void setRegisteredIdentifier(String registeredIdentifier) {
        this.registeredIdentifier = registeredIdentifier;
    }

}
