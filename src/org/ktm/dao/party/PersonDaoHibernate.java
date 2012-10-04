package org.ktm.dao.party;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.ktm.core.KTMContext;
import org.ktm.dao.KTMEMDaoFactory;
import org.ktm.domain.party.Authen;
import org.ktm.domain.party.Person;
import org.ktm.exception.DeleteException;
import org.ktm.utils.HibernateUtil;

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

            Session session = HibernateUtil.getSession(KTMContext.getSession());
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                person = (Person) session.get(getFeaturedClass(), id);
                if (auth != null) {
                    session.delete(auth);
                }
                session.delete(person);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                session.close();
            }
            return person.getUniqueId();
        }
        return -1;
    }
}
