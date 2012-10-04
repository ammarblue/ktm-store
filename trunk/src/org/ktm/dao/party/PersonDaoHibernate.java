package org.ktm.dao.party;

import java.io.Serializable;
import java.util.List;
import org.ktm.dao.KTMEMDaoFactory;
import org.ktm.domain.party.Authen;
import org.ktm.domain.party.Person;
import org.ktm.exception.DeleteException;

public class PersonDaoHibernate extends PartyDaoHibernate implements PersonDao {

    private static final long serialVersionUID = 1L;

    @Override
    public Class<?> getFeaturedClass() {
        return Person.class;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Person> findAll() {
        return (List<Person>) super.findAll();
    }

    @Override
    public int delete(Serializable id) throws DeleteException {
        Person person = null;
        if (getFeaturedClass() != null && id != null) {

            AuthenDao authDao = KTMEMDaoFactory.getInstance().getAuthenDao();
            Authen auth = authDao.findByPartyId((Integer) id);

            try {
                person = (Person) getCurrentSession().get(getFeaturedClass(), id);
                if (auth != null) {
                    getCurrentSession().delete(auth);
                }
                getCurrentSession().delete(person);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return person.getUniqueId();
        }
        return -1;
    }
}
