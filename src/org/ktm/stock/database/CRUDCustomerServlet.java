package org.ktm.stock.database;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ktm.dao.party.CustomerDao;
import org.ktm.dao.party.OrganizationDao;
import org.ktm.domain.party.Customer;
import org.ktm.domain.party.Organization;
import org.ktm.domain.party.PartyRole;
import org.ktm.exception.CreateException;
import org.ktm.exception.DeleteException;
import org.ktm.servlet.ActionForward;
import org.ktm.servlet.CRUDServlet;
import org.ktm.stock.bean.CustomerBean;
import org.ktm.stock.dao.KTMEMDaoFactory;
import org.ktm.web.bean.FormBean;

@WebServlet("/CRUDCustomer")
public class CRUDCustomerServlet extends CRUDServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public String getBeanClass() {
        return "org.ktm.stock.bean.CustomerBean";
    }

    public ActionForward listCustomer(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerDao customerDao = KTMEMDaoFactory.getInstance().getCustomerDao();
        List<Customer> customers = (List<Customer>) customerDao.findAll();
        CustomerBean bean = (CustomerBean) form;
        bean.loadFormCollection(customers);
        return ActionForward.getUri(this, request, "database/ListAllCustomer.jsp");
    }

    public ActionForward newCustomer(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return ActionForward.getUri(this, request, "database/EditCustomer.jsp");
    }

    public ActionForward saveCustomer(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CreateException {
        CustomerBean bean = (CustomerBean) form;

        OrganizationDao orgDao = KTMEMDaoFactory.getInstance().getOrganizationDao();

        Organization org = null;
        Customer customer = null;

        if (!bean.getParty().getUniqueId().isEmpty()) {
            int id = Integer.valueOf(bean.getParty().getUniqueId());
            org = (Organization) orgDao.get(id);
            if (org != null) {
                Set<PartyRole> roles = org.getRoles();
                if (roles != null) {
                    for (PartyRole role : roles) {
                        if (role instanceof Customer) {
                            customer = (Customer) role;
                            break;
                        }
                    }
                }
            } else {
                org = new Organization();
            }
        } else {
            org = new Organization();
        }

        if (customer == null) {
            customer = new Customer();
            org.getRoles().add(customer);
            customer.setParty(org);
        }
        bean.syncToEntity(customer);

        try {
            orgDao.createOrUpdate(org);
        } catch (CreateException e) {
            e.printStackTrace();
        }

        return ActionForward.getAction(this, request, "CRUDCustomer?method=list", true);
    }

    public ActionForward editCustomer(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerBean bean = (CustomerBean) form;

        CustomerDao cEntryDao = KTMEMDaoFactory.getInstance().getCustomerDao();

        int id = Integer.valueOf(bean.getUniqueId());
        Customer cEntry = (Customer) cEntryDao.get(id);
        if (cEntry != null) {
            bean.loadToForm(cEntry);
        }

        return ActionForward.getUri(this, request, "database/EditCustomer.jsp");
    }

    public ActionForward delCustomer(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DeleteException {
        CustomerBean bean = (CustomerBean) form;

        CustomerDao cEntryDao = KTMEMDaoFactory.getInstance().getCustomerDao();

        int id = Integer.valueOf(bean.getUniqueId());
        cEntryDao.delete(id);

        return ActionForward.getAction(this, request, "CRUDCustomer?method=list", true);
    }
}
