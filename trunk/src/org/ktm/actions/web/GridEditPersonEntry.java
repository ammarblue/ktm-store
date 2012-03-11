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

package org.ktm.actions.web;

import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.ktm.actions.KTMAction;
import org.ktm.web.form.FrmPerson;
import org.ktm.web.manager.FormManager;
import org.ktm.web.manager.ServiceLocator;

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


    @Actions({ @Action(value = "/grid-edit-person-entry", results = { @Result(location = "simpleecho.jsp", name = "success"), @Result(location = "simpleecho.jsp", name = "input") }) })
    public String execute() throws Exception {
        FrmPerson frmPerson = new FrmPerson();
        frmPerson.setId(id);
        frmPerson.setIdentifier(identifier);
        frmPerson.setRegisteredIdentifier(registeredIdentifier);
        frmPerson.setPreName(preName);
        frmPerson.setFirstName(firstName);
        frmPerson.setLastName(lastName);
        frmPerson.setBirthDay(birthDay);
        frmPerson.setEmailAddress(emailAddress);
        frmPerson.setTel(tel);
        
        log.debug("id :" + frmPerson.getId());
        log.debug("identifier :" + frmPerson.getIdentifier());
        log.debug("registeredIdentifier :" + frmPerson.getRegisteredIdentifier());
        log.debug("preName :" + frmPerson.getPreName());
        log.debug("firstName :" + frmPerson.getFirstName());
        log.debug("lastName :" + frmPerson.getLastName());
        log.debug("birthDay :" + frmPerson.getBirthDay());
        log.debug("emailAddress:" + frmPerson.getEmailAddress());
        log.debug("tel:" + frmPerson.getTel());

        if (oper.equalsIgnoreCase("del")) {
            StringTokenizer ids = new StringTokenizer(frmPerson.getId(), ",");
            while (ids.hasMoreTokens()) {
                int removeId = Integer.parseInt(ids.nextToken());
                log.debug("Delete Person " + removeId);
                getManager().delete(this, removeId);
            }
        } else {
            if (oper.equalsIgnoreCase("add")) {
                log.debug("Add Person");
                frmPerson.setNew(true);
            } else if (oper.equalsIgnoreCase("edit")) {
                log.debug("Edit Person");
                frmPerson.setNew(false);
            }

            getManager().addOrUpdate(this, frmPerson);
        }
        return SUCCESS;
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

    public String getRegisteredIdentifier() {
        return registeredIdentifier;
    }

    public void setRegisteredIdentifier(String registeredIdentifier) {
        this.registeredIdentifier = registeredIdentifier;
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

    @Override
    protected FormManager getManager() {
        return ServiceLocator.getPersonManager();
    }

}
