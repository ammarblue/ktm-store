package org.ktm.stock.database;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.ktm.dao.KTMEMDaoFactory;
import org.ktm.dao.product.CatalogEntryDao;
import org.ktm.dao.product.ProductTypeDao;
import org.ktm.domain.product.CatalogEntry;
import org.ktm.domain.product.ProductType;
import org.ktm.exception.CreateException;
import org.ktm.exception.DeleteException;
import org.ktm.servlet.ActionForward;
import org.ktm.servlet.CRUDServlet;
import org.ktm.stock.bean.FormBean;
import org.ktm.stock.bean.ProductTypeBean;

@WebServlet("/CRUDProductType")
public class CRUDProductTypeServlet extends CRUDServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected String getBeanClass() {
        return "org.ktm.stock.bean.ProductTypeBean";
    }

    public ActionForward listProductType(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ProductTypeDao productTypeDao = KTMEMDaoFactory.getInstance().getProductTypeDao();
        CatalogEntryDao cEntryDao = KTMEMDaoFactory.getInstance().getCatalogEntryDao();

        ProductTypeBean bean = (ProductTypeBean) form;

        List<CatalogEntry> cEntrys = cEntryDao.findAll();
        bean.loadCatalogEntryFormCollection(cEntrys);

        String selectedCatalogEntry = bean.getSelectedCatalogEntry();
        if (selectedCatalogEntry.isEmpty()) {
            selectedCatalogEntry = (String) session.getAttribute("selectedCatalogEntry");
            if (selectedCatalogEntry == null) {
                if (cEntrys != null && cEntrys.size() > 0) {
                    selectedCatalogEntry = String.valueOf(cEntrys.get(0).getUniqueId());
                }
            }
        }
        session.setAttribute("selectedCatalogEntry", selectedCatalogEntry);

        bean.setSelectedCatalogEntry(selectedCatalogEntry);

        List<ProductType> ptypes = productTypeDao.findByCatalogEntry(Integer.valueOf(selectedCatalogEntry));
        bean.loadFormCollection(ptypes);

        return ActionForward.getUri(this, request, "database/ListAllProductType.jsp");
    }

    public ActionForward newProductType(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return ActionForward.getUri(this, request, "database/EditProductType.jsp");
    }

    public ActionForward saveProductType(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CreateException {
        HttpSession session = request.getSession();
        ProductTypeDao ptypeDao = KTMEMDaoFactory.getInstance().getProductTypeDao();
        CatalogEntryDao cEntryDao = KTMEMDaoFactory.getInstance().getCatalogEntryDao();

        ProductTypeBean bean = (ProductTypeBean) form;

        String selectedCatalogEntry = bean.getSelectedCatalogEntry();
        if (selectedCatalogEntry.isEmpty()) {
            selectedCatalogEntry = (String) session.getAttribute("selectedCatalogEntry");
        }
        CatalogEntry cEntry = (CatalogEntry) cEntryDao.get(Integer.valueOf(selectedCatalogEntry));
        ProductType ptype = new ProductType();

        if (!bean.getUniqueId().isEmpty()) {
            int id = Integer.valueOf(bean.getUniqueId());
            ptype = (ProductType) ptypeDao.get(id);
            if (ptype != null) {
                bean.syncToProductType(ptype);
            }
        } else {
            bean.syncToProductType(ptype);
        }

        ptypeDao.create(ptype);

        cEntry.getProductType().add(ptype);
        cEntryDao.create(cEntry);

        return ActionForward.getAction(this, request, "CRUDProductType?method=list", true);
    }

    public ActionForward editProductType(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductTypeBean bean = (ProductTypeBean) form;

        ProductTypeDao ptypeDao = KTMEMDaoFactory.getInstance().getProductTypeDao();

        int id = Integer.valueOf(bean.getUniqueId());
        ProductType ptype = (ProductType) ptypeDao.get(id);
        if (ptype != null) {
            bean.loadToForm(ptype);
        }

        return ActionForward.getUri(this, request, "database/EditProductType.jsp");
    }

    public ActionForward delProductType(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DeleteException {
        ProductTypeBean bean = (ProductTypeBean) form;

        ProductTypeDao ptypeDao = KTMEMDaoFactory.getInstance().getProductTypeDao();

        int id = Integer.valueOf(bean.getUniqueId());
        ptypeDao.delete(id);

        return ActionForward.getAction(this, request, "CRUDProductType?method=list", true);
    }

}
