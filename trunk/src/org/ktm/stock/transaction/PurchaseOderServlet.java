package org.ktm.stock.transaction;

import javax.servlet.annotation.WebServlet;
import org.ktm.servlet.CRUDServlet;

@WebServlet("/CRUDPurchaseOrder")
public class PurchaseOderServlet extends CRUDServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public String getBeanClass() {
        return "org.ktm.stock.bean.PurchaseOrderBean";
    }

}
