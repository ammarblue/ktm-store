package org.ktm.stock.dao;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import org.ktm.dao.AbstractHibernateStorageDao;
import org.ktm.domain.KTMMaxIdentifier;
import org.ktm.exception.CreateException;
import org.ktm.exception.UpdateException;

public class KTMMaxIdentifierDaoHibernate extends AbstractHibernateStorageDao implements KTMMaxIdentifierDao {

    private static final long serialVersionUID = 1L;

    @Override
    public Class<?> getFeaturedClass() {
        return KTMMaxIdentifier.class;
    }

    private String generateIdentifier(String identifier) {
        String result = "GR";
        Calendar cl = Calendar.getInstance();
        int year = cl.get(Calendar.YEAR);
        int month = cl.get(Calendar.MONTH);
        int day = cl.get(Calendar.DAY_OF_MONTH);

        NumberFormat formatter = new DecimalFormat("00");

        return result + year + formatter.format(month) + formatter.format(day) + "/XX-" + identifier;
    }

    @Override
    public String getMaxPurchaseOrderId() throws UpdateException {
        String result = "00000";
        synchronized (this) {
            KTMMaxIdentifier maxObj = null;
            Collection<?> objs = findAll();

            if (objs.size() <= 0) {
                maxObj = new KTMMaxIdentifier();
                try {
                    createOrUpdate(maxObj);
                } catch (CreateException e) {
                    e.printStackTrace();
                }

                objs = findAll();
            }

            Iterator<?> it = objs.iterator();
            while (it.hasNext()) {
                maxObj = (KTMMaxIdentifier) it.next();
                Integer max = maxObj.getMaxPurchaseOrder();

                NumberFormat formatter = new DecimalFormat("00000");
                result = generateIdentifier(formatter.format(max));

                maxObj.setMaxPurchaseOrder(max + 1);

                update(maxObj);
            }
        }
        return result;
    }

    @Override
    public String getMaxSalesOrderId() throws UpdateException {
        String result = "00000";
        synchronized (this) {
            KTMMaxIdentifier maxObj = null;
            Collection<?> objs = findAll();

            if (objs.size() <= 0) {
                maxObj = new KTMMaxIdentifier();
                try {
                    createOrUpdate(maxObj);
                } catch (CreateException e) {
                    e.printStackTrace();
                }

                objs = findAll();
            }

            Iterator<?> it = objs.iterator();
            while (it.hasNext()) {
                maxObj = (KTMMaxIdentifier) it.next();
                Integer max = maxObj.getMaxSalesOrder();

                NumberFormat formatter = new DecimalFormat("00000");
                result = generateIdentifier(formatter.format(max));

                update(maxObj);
            }
        }
        return result;
    }

}
