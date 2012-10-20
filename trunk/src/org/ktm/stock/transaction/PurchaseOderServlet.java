package org.ktm.stock.transaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.ktm.dao.order.PurchaseOrderDao;
import org.ktm.dao.party.SupplierDao;
import org.ktm.domain.order.PurchaseOrder;
import org.ktm.domain.party.AddressProperties;
import org.ktm.domain.party.EAddressType;
import org.ktm.domain.party.EmailAddress;
import org.ktm.domain.party.GeographicAddress;
import org.ktm.domain.party.Supplier;
import org.ktm.domain.party.TelephoneAddress;
import org.ktm.exception.UpdateException;
import org.ktm.servlet.ActionForward;
import org.ktm.servlet.CRUDServlet;
import org.ktm.stock.bean.PartySummaryBean;
import org.ktm.stock.bean.PurchaseOrderBean;
import org.ktm.stock.dao.KTMEMDaoFactory;
import org.ktm.stock.dao.KTMMaxIdentifierDao;
import org.ktm.web.bean.FormBean;

@WebServlet("/CRUDPurchaseOrder")
public class PurchaseOderServlet extends CRUDServlet {

    private static final long   serialVersionUID = 1L;
    private static final String PURCHASEORDER    = "PURCHASEORDER";

    @Override
    public String getBeanClass() {
        return "org.ktm.stock.bean.PurchaseOrderBean";
    }

    private PurchaseOrderBean getPurchaseOrderBean(HttpServletRequest request) {
        HttpSession session = request.getSession();
        PurchaseOrderBean bean = (PurchaseOrderBean) session.getAttribute(PURCHASEORDER);
        if (bean == null) {
            bean = new PurchaseOrderBean();
            session.setAttribute(PURCHASEORDER, bean);
        }
        return bean;
    }

    public ActionForward getidPurchaseOrder(FormBean form, HttpServletRequest request, HttpServletResponse response) {
        PurchaseOrderBean bean = getPurchaseOrderBean(request);
        if (bean.getIdentifier() == null) {
            KTMMaxIdentifierDao maxIdDao = KTMEMDaoFactory.getInstance().getMaxIdentifierDao();
            try {
                bean.setIdentifier(maxIdDao.getMaxPurchaseOrderId());
            } catch (UpdateException e) {
                e.printStackTrace();
            }
        }

        PrintWriter out = null;
        try {
            out = response.getWriter();
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("identifier", bean.getIdentifier());
            JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(map);

            response.setCharacterEncoding("UTF-8");
            out.print(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ActionForward setempPurchaseOrder(FormBean form, HttpServletRequest request, HttpServletResponse response) {
        PurchaseOrderBean purchaseOrderBean = getPurchaseOrderBean(request);
        PurchaseOrderBean bean = (PurchaseOrderBean) form;
        SupplierDao supplierDao = KTMEMDaoFactory.getInstance().getSupplierDao();
        Supplier supplier = (Supplier) supplierDao.findByPartyIdentifier(bean.getPartySummary().getPartyIdentifier());
        if (supplier != null) {
            PartySummaryBean partySummaryBean = purchaseOrderBean.getPartySummary();
            partySummaryBean.setPartyIdentifier(supplier.getParty().getIdentifier().getIdentifier());
            partySummaryBean.setName(supplier.getDescription());
            Set<AddressProperties> addrs = supplier.getParty().getAddresses();
            String address = "";
            for (AddressProperties addr : addrs) {
                if (EAddressType.parse(addr.getUseage()) == EAddressType.GEOGRAPHICS) {
                    address = ((GeographicAddress) addr.getAddress()).getAddressLine1();
                    partySummaryBean.setAdress(address);
                }
                if (EAddressType.parse(addr.getUseage()) == EAddressType.TELEPHONE) {
                    address = ((TelephoneAddress) addr.getAddress()).getNumber();
                    partySummaryBean.setTelephoneNumber(address);
                }
                if (EAddressType.parse(addr.getUseage()) == EAddressType.EMAIL) {
                    address = ((EmailAddress) addr.getAddress()).getEmail();
                    partySummaryBean.setEmailAddress(address);
                }
            }

            try {
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                address = partySummaryBean.getAdress();
                address += "<br>tel: " + partySummaryBean.getTelephoneNumber();
                address += "<br>e-mail: " + partySummaryBean.getEmailAddress();
                out.print(address);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public ActionForward listPurchaseOrder(FormBean form, HttpServletRequest request, HttpServletResponse response) {
        PurchaseOrderBean bean = (PurchaseOrderBean) form;
        PurchaseOrderDao purchaseOrderDao = KTMEMDaoFactory.getInstance().getPurchaseOrderDao();
        List<PurchaseOrder> purchaseOrders = purchaseOrderDao.findAll();
        bean.loadFormCollection(purchaseOrders);
        return ActionForward.getUri(this, request, "transaction/ListPurchaseOrder.jsp");
    }

    public ActionForward newPurchaseOrder(FormBean form, HttpServletRequest request, HttpServletResponse response) {
        return ActionForward.getUri(this, request, "transaction/EditPurchaseOrder.jsp");
    }

    public ActionForward savePurchaseOrder(FormBean form, HttpServletRequest request, HttpServletResponse response) {
        PurchaseOrderBean bean = (PurchaseOrderBean) form;
        PurchaseOrderDao purchaseOrderDao = KTMEMDaoFactory.getInstance().getPurchaseOrderDao();

        return ActionForward.getAction(this, request, "CRUDPurchaseOrder?method=list", true);
    }

}
