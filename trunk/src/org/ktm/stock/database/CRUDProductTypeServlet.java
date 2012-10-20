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
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.ktm.dao.product.CatalogEntryTypeDao;
import org.ktm.dao.product.MeasuredProductTypeDao;
import org.ktm.domain.money.Price;
import org.ktm.domain.product.CatalogEntryType;
import org.ktm.domain.product.MeasuredProductType;
import org.ktm.domain.product.ProductType;
import org.ktm.exception.KTMException;
import org.ktm.servlet.ActionForward;
import org.ktm.servlet.CRUDServlet;
import org.ktm.stock.bean.ProductTypeBean;
import org.ktm.stock.dao.KTMEMDaoFactory;
import org.ktm.web.bean.FormBean;

@WebServlet("/CRUDProductType")
public class CRUDProductTypeServlet extends CRUDServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public String getBeanClass() {
        return "org.ktm.stock.bean.ProductTypeBean";
    }

    @SuppressWarnings("unchecked")
    public ActionForward listjsonProductType(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MeasuredProductTypeDao measuredProductTypeDao = KTMEMDaoFactory.getInstance().getMeasuredProductTypeDao();
        List<MeasuredProductType> measuredProductTypes = (List<MeasuredProductType>) measuredProductTypeDao.findAll();
        JSONArray jsonArray = new JSONArray();
        for (MeasuredProductType measuredProductType : measuredProductTypes) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("identifier", measuredProductType.getIdentifier().getIdentifier());
            map.put("name", measuredProductType.getName());
            Set<Price> prices = measuredProductType.getPrices();
            for (Price price : prices) {
                if (price.getValidTo() == null) {
                    map.put("price", String.valueOf(price.getAmount().getAmount()));
                    break;
                }
            }
            map.put("unit", measuredProductType.getMetric() == null ? "" : measuredProductType.getMetric().getSymbol());
            JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(map);
            jsonArray.add(jsonObject);
        }

        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonArray.toString());
        return null;
    }

    private String getSelectedCatalogEntryType(HttpSession session, ProductTypeBean bean) throws KTMException {
        String selectedCatalogEntryType = bean.getSelectedCatalogEntryType();
        if (selectedCatalogEntryType.isEmpty()) {
            selectedCatalogEntryType = (String) session.getAttribute("selectedCatalogEntryType");
            if (selectedCatalogEntryType == null) {
                CatalogEntryTypeDao cEntryTypeDao = KTMEMDaoFactory.getInstance().getCatalogEntryTypeDao();
                List<CatalogEntryType> cEntrys = cEntryTypeDao.findAll();
                if (cEntrys != null && cEntrys.size() > 0) {
                    selectedCatalogEntryType = String.valueOf(cEntrys.get(0).getUniqueId());
                } else {
                    String msg = "exception.not_found_entity: " + CatalogEntryType.class.getName();
                    throw new org.ktm.exception.KTMException(msg);
                }
            }
        }
        session.setAttribute("selectedCatalogEntryType", selectedCatalogEntryType);
        return selectedCatalogEntryType;
    }

    public ActionForward listProductType(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, KTMException {
        HttpSession session = request.getSession();
        MeasuredProductTypeDao productTypeDao = KTMEMDaoFactory.getInstance().getMeasuredProductTypeDao();
        CatalogEntryTypeDao cEntryTypeDao = KTMEMDaoFactory.getInstance().getCatalogEntryTypeDao();

        ProductTypeBean bean = (ProductTypeBean) form;

        List<CatalogEntryType> cEntrys = cEntryTypeDao.findAll();
        bean.loadCatalogEntryTypeFormCollection(cEntrys);

        String selectedCatalogEntryType = getSelectedCatalogEntryType(session, bean);
        bean.setSelectedCatalogEntryType(selectedCatalogEntryType);

        List<ProductType> ptypes = productTypeDao.findByCatalogEntryType(Integer.valueOf(selectedCatalogEntryType));
        bean.loadFormCollection(ptypes);

        return ActionForward.getUri(this, request, "database/ListAllProductType.jsp");
    }

    public ActionForward newProductType(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, KTMException {
        HttpSession session = request.getSession();
        ProductTypeBean bean = (ProductTypeBean) form;

        CatalogEntryTypeDao cEntryTypeDao = KTMEMDaoFactory.getInstance().getCatalogEntryTypeDao();

        String selectedCatalogEntryType = getSelectedCatalogEntryType(session, bean);
        CatalogEntryType cEntry = (CatalogEntryType) cEntryTypeDao.get(Integer.valueOf(selectedCatalogEntryType));

        bean.setCatalogEntryTypeName(cEntry.getName());

        return ActionForward.getUri(this, request, "database/EditProductType.jsp");
    }

    public ActionForward saveProductType(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, KTMException {
        HttpSession session = request.getSession();
        MeasuredProductTypeDao ptypeDao = KTMEMDaoFactory.getInstance().getMeasuredProductTypeDao();
        CatalogEntryTypeDao cEntryTypeDao = KTMEMDaoFactory.getInstance().getCatalogEntryTypeDao();

        ProductTypeBean bean = (ProductTypeBean) form;

        String selectedCatalogEntryType = getSelectedCatalogEntryType(session, bean);

        CatalogEntryType cEntry = (CatalogEntryType) cEntryTypeDao.get(Integer.valueOf(selectedCatalogEntryType));
        MeasuredProductType ptype = new MeasuredProductType();

        if (!bean.getUniqueId().isEmpty()) {
            int id = Integer.valueOf(bean.getUniqueId());
            ptype = (MeasuredProductType) ptypeDao.get(id);
            if (ptype != null) {
                bean.syncToEntity(ptype);
            }
        } else {
            bean.syncToEntity(ptype);
            cEntry.getProductType().add(ptype);
            ptype.setCataloEntryType(cEntry);
        }
        // ptypeDao.createOrUpdate(ptype);
        cEntryTypeDao.createOrUpdate(cEntry);

        return ActionForward.getAction(this, request, "CRUDProductType?method=list", true);
    }

    public ActionForward editProductType(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, KTMException {
        HttpSession session = request.getSession();
        ProductTypeBean bean = (ProductTypeBean) form;

        MeasuredProductTypeDao ptypeDao = KTMEMDaoFactory.getInstance().getMeasuredProductTypeDao();
        CatalogEntryTypeDao cEntryTypeDao = KTMEMDaoFactory.getInstance().getCatalogEntryTypeDao();

        int id = Integer.valueOf(bean.getUniqueId());
        MeasuredProductType ptype = (MeasuredProductType) ptypeDao.get(id);
        if (ptype != null) {
            bean.loadToForm(ptype);
        }

        String selectedCatalogEntryType = getSelectedCatalogEntryType(session, bean);
        CatalogEntryType cEntry = (CatalogEntryType) cEntryTypeDao.get(Integer.valueOf(selectedCatalogEntryType));

        bean.setCatalogEntryTypeName(cEntry.getName());

        return ActionForward.getUri(this, request, "database/EditProductType.jsp");
    }

    public ActionForward delProductType(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, KTMException {
        HttpSession session = request.getSession();
        ProductTypeBean bean = (ProductTypeBean) form;

        MeasuredProductTypeDao ptypeDao = KTMEMDaoFactory.getInstance().getMeasuredProductTypeDao();
        CatalogEntryTypeDao cEntryTypeDao = KTMEMDaoFactory.getInstance().getCatalogEntryTypeDao();

        String selectedCatalogEntryType = getSelectedCatalogEntryType(session, bean);
        CatalogEntryType cEntry = (CatalogEntryType) cEntryTypeDao.get(Integer.valueOf(selectedCatalogEntryType));

        if (cEntry != null) {
            int id = Integer.valueOf(bean.getUniqueId());
            MeasuredProductType ptype = (MeasuredProductType) ptypeDao.get(id);
            if (ptype != null) {
                cEntry.getProductType().remove(ptype);
                ptype.setCataloEntryType(null);
                cEntryTypeDao.update(cEntry);
                ptypeDao.delete(ptype);
            }
        }
        return ActionForward.getAction(this, request, "CRUDProductType?method=list", true);
    }
}
