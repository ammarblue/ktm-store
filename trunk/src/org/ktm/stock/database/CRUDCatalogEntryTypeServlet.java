package org.ktm.stock.database;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ktm.dao.product.CatalogEntryTypeDao;
import org.ktm.domain.product.CatalogEntryType;
import org.ktm.exception.CreateException;
import org.ktm.exception.DeleteException;
import org.ktm.exception.UpdateException;
import org.ktm.servlet.ActionForward;
import org.ktm.servlet.CRUDServlet;
import org.ktm.stock.bean.CatalogEntryTypeBean;
import org.ktm.stock.dao.KTMEMDaoFactory;
import org.ktm.web.bean.FormBean;

@WebServlet("/CRUDCatalogEntryType")
public class CRUDCatalogEntryTypeServlet extends CRUDServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public String getBeanClass() {
        return "org.ktm.stock.bean.CatalogEntryTypeBean";
    }

    public ActionForward listCatalogEntryType(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CatalogEntryTypeDao catalogEntryDao = KTMEMDaoFactory.getInstance().getCatalogEntryTypeDao();
        List<CatalogEntryType> catalogEntrys = catalogEntryDao.findAll();
        CatalogEntryTypeBean bean = (CatalogEntryTypeBean) form;
        bean.loadFormCollection(catalogEntrys);
        return ActionForward.getUri(this, request, "database/ListAllCatalogEntryType.jsp");
    }

    public ActionForward newCatalogEntryType(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return ActionForward.getUri(this, request, "database/EditCatalogEntryType.jsp");
    }

    public ActionForward saveCatalogEntryType(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CreateException, UpdateException {
        CatalogEntryTypeBean bean = (CatalogEntryTypeBean) form;

        CatalogEntryTypeDao cEntryDao = KTMEMDaoFactory.getInstance().getCatalogEntryTypeDao();

        CatalogEntryType cEntry = new CatalogEntryType();

        if (!bean.getUniqueId().isEmpty()) {
            int id = Integer.valueOf(bean.getUniqueId());
            cEntry = (CatalogEntryType) cEntryDao.get(id);
            if (cEntry != null) {
                bean.syncToCatalogEntryType(cEntry);
            }
            cEntryDao.update(cEntry);
        } else {
            bean.syncToCatalogEntryType(cEntry);
            cEntryDao.createOrUpdate(cEntry);
        }

        return ActionForward.getAction(this, request, "CRUDCatalogEntryType?method=list", true);
    }

    public ActionForward editCatalogEntryType(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CatalogEntryTypeBean bean = (CatalogEntryTypeBean) form;

        CatalogEntryTypeDao cEntryDao = KTMEMDaoFactory.getInstance().getCatalogEntryTypeDao();

        int id = Integer.valueOf(bean.getUniqueId());
        CatalogEntryType cEntry = (CatalogEntryType) cEntryDao.get(id);
        if (cEntry != null) {
            bean.loadToForm(cEntry);
        }

        return ActionForward.getUri(this, request, "database/EditCatalogEntryType.jsp");
    }

    public ActionForward delCatalogEntryType(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DeleteException {
        CatalogEntryTypeBean bean = (CatalogEntryTypeBean) form;

        CatalogEntryTypeDao cEntryDao = KTMEMDaoFactory.getInstance().getCatalogEntryTypeDao();

        int id = Integer.valueOf(bean.getUniqueId());
        cEntryDao.delete(id);

        return ActionForward.getAction(this, request, "CRUDCatalogEntryType?method=list", true);
    }

    // public ActionForward storeCatalogEntryType(FormBean form,
    // HttpServletRequest
    // request, HttpServletResponse response) throws ServletException,
    // IOException, DeleteException {
    // store(request, response);
    // closeSession(request);
    // return ActionForward.getAction(this, request,
    // "CRUDCatalogEntryType?method=list", true);
    // }

}
