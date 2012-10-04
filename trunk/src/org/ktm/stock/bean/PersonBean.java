package org.ktm.stock.bean;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.ktm.domain.party.ISOGender;
import org.ktm.domain.party.PartyIdentifier;
import org.ktm.domain.party.Person;
import org.ktm.utils.DateUtils;

public class PersonBean extends FormBean {

    private String           citizenId;
    private String           prename;
    private String           firstname;
    private String           lastname;
    private String           birthDay;
    private ISOGender        gender;

    private String           username;
    private String           password;

    private List<PersonBean> personCollection;

    public PersonBean() {
        reset();
    }

    @Override
    public void reset() {
        super.reset();
        setCitizenId("");
        setPrename("");
        setFirstname("");
        setLastname("");
        setBirthDay("");
        setGender(ISOGender.MALE);

        setUsername("");
        setPassword("");

        personCollection = new ArrayList<PersonBean>();
    }

    public void loadToForm(Person person) {
        if (person != null) {
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

            // TODO: sync more additional properties
        }
    }

    public void syncToPerson(Person person) {
        if (person != null) {
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

    public void loadFormCollection(List<Person> persons) {
        if (persons != null && persons.size() > 0) {
            personCollection.clear();

            for (Person person : persons) {
                PersonBean bean = new PersonBean();
                bean.loadToForm(person);
                personCollection.add(bean);
            }
        }
    }

}
