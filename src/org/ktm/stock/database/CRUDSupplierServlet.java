package org.ktm.stock.database;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.ktm.dao.party.OrganizationDao;
import org.ktm.dao.party.SupplierDao;
import org.ktm.domain.party.Organization;
import org.ktm.domain.party.PartyRole;
import org.ktm.domain.party.Supplier;
import org.ktm.exception.CreateException;
import org.ktm.exception.DeleteException;
import org.ktm.servlet.ActionForward;
import org.ktm.servlet.CRUDServlet;
import org.ktm.stock.bean.SupplierBean;
import org.ktm.stock.dao.KTMEMDaoFactory;
import org.ktm.web.bean.FormBean;

@WebServlet("/CRUDSupplier")
public class CRUDSupplierServlet extends CRUDServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public String getBeanClass() {
        return "org.ktm.stock.bean.SupplierBean";
    }

    public ActionForward listjsonSupplier(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SupplierDao supplierDao = KTMEMDaoFactory.getInstance().getSupplierDao();
        List<Supplier> suppliers = (List<Supplier>) supplierDao.findAll();
        JSONArray jsonArray = new JSONArray();
        for (Supplier supplier : suppliers) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("identifier", supplier.getParty().getIdentifier().getIdentifier());
            map.put("name", supplier.getDescription());
            JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(map);
            jsonArray.add(jsonObject);
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(jsonArray);
        return null;
    }

    public ActionForward listSupplier(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SupplierDao supplierDao = KTMEMDaoFactory.getInstance().getSupplierDao();
        List<Supplier> suppliers = (List<Supplier>) supplierDao.findAll();
        SupplierBean bean = (SupplierBean) form;
        bean.loadFormCollection(suppliers);
        return ActionForward.getUri(this, request, "database/ListAllSupplier.jsp");
    }

    public ActionForward newSupplier(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return ActionForward.getUri(this, request, "database/EditSupplier.jsp");
    }

    public ActionForward saveSupplier(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CreateException {
        SupplierBean bean = (SupplierBean) form;

        OrganizationDao orgDao = KTMEMDaoFactory.getInstance().getOrganizationDao();

        Organization org = null;
        Supplier supplier = null;

        if (!bean.getParty().getUniqueId().isEmpty()) {
            int id = Integer.valueOf(bean.getParty().getUniqueId());
            org = (Organization) orgDao.get(id);
            if (org != null) {
                Set<PartyRole> roles = org.getRoles();
                if (roles != null) {
                    for (PartyRole role : roles) {
                        if (role instanceof Supplier) {
                            supplier = (Supplier) role;
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

        if (supplier == null) {
            supplier = new Supplier();
            org.getRoles().add(supplier);
            supplier.setParty(org);
        }
        bean.syncToEntity(supplier);

        try {
            orgDao.createOrUpdate(org);
        } catch (CreateException e) {
            e.printStackTrace();
        }

        return ActionForward.getAction(this, request, "CRUDSupplier?method=list", true);
    }

    public ActionForward editSupplier(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SupplierBean bean = (SupplierBean) form;

        SupplierDao cEntryDao = KTMEMDaoFactory.getInstance().getSupplierDao();

        int id = Integer.valueOf(bean.getUniqueId());
        Supplier cEntry = (Supplier) cEntryDao.get(id);
        if (cEntry != null) {
            bean.loadToForm(cEntry);
        }

        return ActionForward.getUri(this, request, "database/EditSupplier.jsp");
    }

    public ActionForward delSupplier(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DeleteException {
        SupplierBean bean = (SupplierBean) form;

        SupplierDao cEntryDao = KTMEMDaoFactory.getInstance().getSupplierDao();

        int id = Integer.valueOf(bean.getUniqueId());
        cEntryDao.delete(id);

        return ActionForward.getAction(this, request, "CRUDSupplier?method=list", true);
    }
}
