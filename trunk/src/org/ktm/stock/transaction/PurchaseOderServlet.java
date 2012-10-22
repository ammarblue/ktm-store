package org.ktm.stock.transaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.ktm.dao.order.OrderLineDao;
import org.ktm.dao.order.PurchaseOrderDao;
import org.ktm.dao.party.PartyDao;
import org.ktm.dao.party.SupplierDao;
import org.ktm.domain.money.Money;
import org.ktm.domain.order.OrderLine;
import org.ktm.domain.order.OrderLineIdentifier;
import org.ktm.domain.order.PurchaseOrder;
import org.ktm.domain.party.AddressProperties;
import org.ktm.domain.party.EAddressType;
import org.ktm.domain.party.EmailAddress;
import org.ktm.domain.party.GeographicAddress;
import org.ktm.domain.party.Organization;
import org.ktm.domain.party.Party;
import org.ktm.domain.party.PartyRole;
import org.ktm.domain.party.Supplier;
import org.ktm.domain.party.TelephoneAddress;
import org.ktm.domain.product.ProductIdentifier;
import org.ktm.exception.UpdateException;
import org.ktm.servlet.ActionForward;
import org.ktm.servlet.CRUDServlet;
import org.ktm.stock.bean.OrderLineBean;
import org.ktm.stock.bean.PartySummaryBean;
import org.ktm.stock.bean.PurchaseOrderBean;
import org.ktm.stock.dao.KTMEMDaoFactory;
import org.ktm.stock.dao.KTMMaxIdentifierDao;
import org.ktm.utils.DateUtils;
import org.ktm.web.bean.FormBean;
import org.ktm.web.tags.Functions;

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

    public ActionForward getidPurchaseOrder(FormBean form,
            HttpServletRequest request,
            HttpServletResponse response) {
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
            response.setContentType("application/json");
            out.print(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ActionForward setempPurchaseOrder(FormBean form,
            HttpServletRequest request,
            HttpServletResponse response) {
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

    public ActionForward listPurchaseOrder(FormBean form,
            HttpServletRequest request,
            HttpServletResponse response) {
        PurchaseOrderBean bean = (PurchaseOrderBean) form;
        PurchaseOrderDao purchaseOrderDao = KTMEMDaoFactory.getInstance().getPurchaseOrderDao();
        List<PurchaseOrder> purchaseOrders = purchaseOrderDao.findAll();
        bean.loadFormCollection(purchaseOrders);
        return ActionForward.getUri(this,
                request,
                "transaction/ListPurchaseOrder.jsp");
    }

    public ActionForward newPurchaseOrder(FormBean form,
            HttpServletRequest request,
            HttpServletResponse response) {
        return ActionForward.getUri(this,
                request,
                "transaction/EditPurchaseOrder.jsp");
    }

    public ActionForward savePurchaseOrder(FormBean form,
            HttpServletRequest request,
            HttpServletResponse response) {
        PurchaseOrderBean bean = (PurchaseOrderBean) form;
        PurchaseOrderDao purchaseOrderDao = KTMEMDaoFactory.getInstance().getPurchaseOrderDao();
        OrderLineDao orderLineDao = KTMEMDaoFactory.getInstance().getOrderLineDao();

        String jb = request.getParameter("data");
        JSONObject jsonObject = JSONObject.fromObject(jb);

        String orderUniqueId = jsonObject.getString("order_unique_id");
        String dateCreated = jsonObject.getString("date_created");
        String supplierId = jsonObject.getString("supplier_id");
        String orderId = jsonObject.getString("order_id");
        JSONArray jsonOrderLines = jsonObject.getJSONArray("orderlines");

        bean.setDateCreated(dateCreated);
        bean.setIdentifier(orderId);
        bean.setPartySummary(getPartySummary(supplierId));

        PurchaseOrder purchaseOrder = purchaseOrderDao.findByOrderId(orderId);
        if (purchaseOrder == null) {
            purchaseOrder = new PurchaseOrder();
        }

        Set<OrderLine> orderLines = purchaseOrder.getOrderLines();
        if (orderLines == null) {
            orderLines = new HashSet<OrderLine>();
            purchaseOrder.setOrderLines(orderLines);
        }

        for (int i = 0; i < jsonOrderLines.size(); i++) {
            JSONObject jsonOrderLine = jsonOrderLines.getJSONObject(i);
            String uniqueId = jsonOrderLine.getString("unique_id");
            String productId = jsonOrderLine.getString("product_id");
            String productDesc = jsonOrderLine.getString("product_desc");
            String productQuanitity = jsonOrderLine.getString("product_quanitity");
            String productTotal = jsonOrderLine.getString("product_total");

            OrderLine orderLine = null;
            if (!Functions.isEmpty(orderUniqueId) && !Functions.isEmpty(uniqueId)) {
                Integer orderLineId = Integer.valueOf(uniqueId);
                orderLine = (OrderLine) orderLineDao.get(orderLineId);
            }
            if (orderLine == null) {
                orderLine = new OrderLine();
            }

            orderLine.setOrder(purchaseOrder);
            OrderLineIdentifier identifier = orderLine.getIdentifier();
            if (identifier == null) {
                identifier = new OrderLineIdentifier();
                orderLine.setIdentifier(identifier);
            }
            identifier.setIdentifier(uniqueId + "-" + productId);
            ProductIdentifier productIdentifier = orderLine.getProductType();
            if (productIdentifier == null) {
                productIdentifier = new ProductIdentifier();
                orderLine.setProductType(productIdentifier);
            }
            productIdentifier.setIdentifier(productId);
            orderLine.setDescription(productDesc);
            orderLine.setNumberOrdered(Integer.parseInt(productQuanitity));
            try {
                orderLine.setExpectedDeliveryDate(DateUtils.formatString(dateCreated));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Money money = orderLine.getUnitPrice();
            if (money == null) {
                money = new Money();
                orderLine.setUnitPrice(money);
            }
            money.setAmount(Double.parseDouble(productTotal));

            if (orderLines.contains(orderLine)) {
                OrderLine entity = orderLineDao.findByIdentifier(orderLine.getIdentifier());
                if (entity != null) {
                    OrderLineBean newBean = new OrderLineBean();
                    newBean.mergeEntity(entity, orderLine);
                }
            } else {

            }
        }
        return null;
    }

    public static String getJSONPurchaseOrder(HttpServletRequest request) {
        String result = "{}"; // "{'supplier_desc': 'desc'," +
                              // "'order_unique_id':'uid'," +
                              // "'date_created': 'now'," +
                              // "'supplier_id': 'supplier'," +
                              // "'order_id': 'xxxx'," + "'order_lines': [" +
                              // "{'unique_id':'uid','product_id':'pid','product_desc':'desc','product_unit':'unit','product_price':'price','product_quanitity':'quan','product_total':'total'},"
                              // + "{}" + "]}";
        return result;
    }

    private PartySummaryBean getPartySummary(String supplierId) {
        PartySummaryBean result = new PartySummaryBean();
        PartyDao partyDao = KTMEMDaoFactory.getInstance().getPartyDao();
        Party party = partyDao.findByIdentifier(supplierId);
        if (party instanceof Organization) {
            Organization org = (Organization) party;
            Set<PartyRole> roles = org.getRoles();
            for (PartyRole role : roles) {
                if (role instanceof Supplier) {
                    result.loadToForm(role);
                    break;
                }
            }
        }
        return result;
    }

}
