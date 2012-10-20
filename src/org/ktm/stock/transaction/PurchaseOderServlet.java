package org.ktm.stock.transaction;

import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ktm.dao.order.PurchaseOrderDao;
import org.ktm.domain.order.PurchaseOrder;
import org.ktm.servlet.ActionForward;
import org.ktm.servlet.CRUDServlet;
import org.ktm.stock.bean.PurchaseOrderBean;
import org.ktm.stock.dao.KTMEMDaoFactory;
import org.ktm.web.bean.FormBean;

@WebServlet("/CRUDPurchaseOrder")
public class PurchaseOderServlet extends CRUDServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public String getBeanClass() {
        return "org.ktm.stock.bean.PurchaseOrderBean";
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
