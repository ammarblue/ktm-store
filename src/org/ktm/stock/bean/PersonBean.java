package org.ktm.stock.bean;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.ktm.domain.KTMEntity;
import org.ktm.domain.party.ISOGender;
import org.ktm.domain.party.PartyIdentifier;
import org.ktm.domain.party.Person;
import org.ktm.utils.DateUtils;

public class PersonBean extends PartyBean {

    private String           citizenId;
    private String           prename;
    private String           firstname;
    private String           lastname;
    private String           birthDay;
    private ISOGender        gender;

    private String           username;
    private String           password;

    private List<PersonBean> personCollection;

    @Override
    public void reset() {
        super.reset();
        setGender(ISOGender.MALE);

        personCollection = new ArrayList<PersonBean>();
    }

    @Override
    public void loadToForm(KTMEntity entity) {
        if (entity != null && entity instanceof Person) {
            super.loadToForm(entity);
            Person person = (Person) entity;
            this.setUniqueId(String.valueOf(person.getUniqueId()));
            this.setPrename(person.getPrename());
            this.setFirstname(person.getFirstname());
            this.setLastname(person.getLastname());
            String identifier = "";
            if (person.getIdentifier() != null) {
                identifier = person.getIdentifier().getIdentifier();
            }
            this.setCitizenId(identifier);
            try {
                this.setBirthDay(DateUtils.formatDate(person.getBirthDay()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.setGender(person.getGender());
        }
    }

    @Override
    public void syncToEntity(KTMEntity entity) {
        if (entity != null && entity instanceof Person) {
            Person person = (Person) entity;
            if (!this.getUniqueId().isEmpty()) {
                person.setUniqueId(Integer.valueOf(this.getUniqueId()));
            }
            person.setGender(this.getGender());
            person.setPrename(this.getPrename());
            person.setFirstname(this.getFirstname());
            person.setLastname(this.getLastname());
            PartyIdentifier identifier = person.getIdentifier();
            if (identifier == null) {
                identifier = new PartyIdentifier();
                person.setIdentifier(identifier);
            }
            identifier.setIdentifier(this.getCitizenId());
            person.setBirthDay(this.getBirthDay());

            // TODO: sync more additional properties
        }
    }

    public String getPrename() {
        return prename;
    }

    public void setPrename(String prename) {
        this.prename = prename;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public ISOGender getGender() {
        return gender;
    }

    public void setGender(ISOGender gender) {
        this.gender = gender;
    }

    public String getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PersonBean> getPersonCollection() {
        return personCollection;
    }

    public void setPersonCollection(List<PersonBean> personCollection) {
        this.personCollection = personCollection;
    }

    @Override
    public void loadFormCollection(Collection<?> entitys) {
        if (entitys != null) {
            personCollection.clear();

            for (Object entity : entitys) {
                if (entity instanceof Person) {
                    PersonBean bean = new PersonBean();
                    bean.loadToForm((Person) entity);
                    personCollection.add(bean);
                }
            }
        }
    }

}
