package org.ktm.stock.database;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ktm.dao.KTMEMDaoFactory;
import org.ktm.dao.product.CatalogEntryDao;
import org.ktm.domain.product.CatalogEntry;
import org.ktm.exception.CreateException;
import org.ktm.exception.DeleteException;
import org.ktm.exception.UpdateException;
import org.ktm.servlet.ActionForward;
import org.ktm.servlet.CRUDServlet;
import org.ktm.stock.bean.CatalogEntryBean;
import org.ktm.stock.bean.FormBean;

@WebServlet("/CRUDCatalogEntry")
public class CRUDCatalogEntryServlet extends CRUDServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public String getBeanClass() {
        return "org.ktm.stock.bean.CatalogEntryBean";
    }

    public ActionForward listCatalogEntry(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CatalogEntryDao catalogEntryDao = KTMEMDaoFactory.getInstance().getCatalogEntryDao();
        List<CatalogEntry> catalogEntrys = catalogEntryDao.findAll();
        CatalogEntryBean bean = (CatalogEntryBean) form;
        bean.loadFormCollection(catalogEntrys);
        return ActionForward.getUri(this, request, "database/ListAllCatalogEntry.jsp");
    }

    public ActionForward newCatalogEntry(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return ActionForward.getUri(this, request, "database/EditCatalogEntry.jsp");
    }

    public ActionForward saveCatalogEntry(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CreateException, UpdateException {
        CatalogEntryBean bean = (CatalogEntryBean) form;

        CatalogEntryDao cEntryDao = KTMEMDaoFactory.getInstance().getCatalogEntryDao();

        CatalogEntry cEntry = new CatalogEntry();

        if (!bean.getUniqueId().isEmpty()) {
            int id = Integer.valueOf(bean.getUniqueId());
            cEntry = (CatalogEntry) cEntryDao.get(id);
            if (cEntry != null) {
                bean.syncToCatalogEntry(cEntry);
            }
            cEntryDao.update(cEntry);
        } else {
            bean.syncToCatalogEntry(cEntry);
            cEntryDao.createOrUpdate(cEntry);
        }

        return ActionForward.getAction(this, request, "CRUDCatalogEntry?method=list", true);
    }

    public ActionForward editCatalogEntry(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CatalogEntryBean bean = (CatalogEntryBean) form;

        CatalogEntryDao cEntryDao = KTMEMDaoFactory.getInstance().getCatalogEntryDao();

        int id = Integer.valueOf(bean.getUniqueId());
        CatalogEntry cEntry = (CatalogEntry) cEntryDao.get(id);
        if (cEntry != null) {
            bean.loadToForm(cEntry);
        }

        return ActionForward.getUri(this, request, "database/EditCatalogEntry.jsp");
    }

    public ActionForward delCatalogEntry(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DeleteException {
        CatalogEntryBean bean = (CatalogEntryBean) form;

        CatalogEntryDao cEntryDao = KTMEMDaoFactory.getInstance().getCatalogEntryDao();

        int id = Integer.valueOf(bean.getUniqueId());
        cEntryDao.delete(id);

        return ActionForward.getAction(this, request, "CRUDCatalogEntry?method=list", true);
    }

    // public ActionForward storeCatalogEntry(FormBean form, HttpServletRequest
    // request, HttpServletResponse response) throws ServletException,
    // IOException, DeleteException {
    // store(request, response);
    // closeSession(request);
    // return ActionForward.getAction(this, request,
    // "CRUDCatalogEntry?method=list", true);
    // }

}
