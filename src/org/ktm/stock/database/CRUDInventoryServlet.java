package org.ktm.stock.database;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.ktm.dao.product.EInventoryType;
import org.ktm.dao.product.InventoryDao;
import org.ktm.domain.product.CenterInventory;
import org.ktm.domain.product.Inventory;
import org.ktm.domain.product.SalePointInventory;
import org.ktm.domain.product.VehicleInventory;
import org.ktm.exception.KTMException;
import org.ktm.servlet.ActionForward;
import org.ktm.servlet.CRUDServlet;
import org.ktm.stock.bean.InventoryBean;
import org.ktm.stock.dao.KTMEMDaoFactory;
import org.ktm.utils.Localizer;
import org.ktm.web.bean.FormBean;

@WebServlet("/CRUDInventory")
public class CRUDInventoryServlet extends CRUDServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public String getBeanClass() {
        return "org.ktm.stock.bean.InventoryBean";
    }

    private String getSelectedInventoryType(HttpSession session, InventoryBean bean) throws KTMException {
        String selectedInventoryType = bean.getSelectedInventoryType();
        if (selectedInventoryType.isEmpty()) {
            selectedInventoryType = (String) session.getAttribute("selectedInventoryType");
            if (selectedInventoryType == null) {
                selectedInventoryType = EInventoryType.values()[0].toString();
            }
        }
        session.setAttribute("selectedInventoryType", selectedInventoryType);
        return selectedInventoryType;
    }

    public ActionForward listInventory(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, KTMException {
        HttpSession session = request.getSession();
        InventoryBean bean = (InventoryBean) form;

        InventoryDao inventoryDao = KTMEMDaoFactory.getInstance().getInventoryDao();
        String selectedInventoryType = getSelectedInventoryType(session, bean);

        EInventoryType type = EInventoryType.parse(selectedInventoryType);
        List<Inventory> inventoryTypeList = inventoryDao.findByInventoryType(type);
        bean.loadFormCollection(inventoryTypeList);
        bean.setSelectedInventoryType(selectedInventoryType);

        return ActionForward.getUri(this, request, "database/ListAllInventory.jsp");
    }

    public ActionForward newInventory(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, KTMException {
        InventoryBean bean = (InventoryBean) form;
        String selectedInventoryType = getSelectedInventoryType(request.getSession(), bean);
        bean.setSelectedInventoryType(Localizer.getMessage(selectedInventoryType));

        return ActionForward.getUri(this, request, "database/EditInventory.jsp");
    }

    private InventoryDao getInventoryDao(InventoryBean bean) throws KTMException {
        InventoryDao inventoryDao = null;
        if (bean != null) {
            EInventoryType type = EInventoryType.parse(bean.getSelectedInventoryType());
            switch (type) {
                case CENTER:
                    inventoryDao = KTMEMDaoFactory.getInstance().getCenterInventoryDao();
                    break;
                case MOVING:
                    inventoryDao = KTMEMDaoFactory.getInstance().getVechileInventoryDao();
                    break;
                case SALE_POINT:
                    inventoryDao = KTMEMDaoFactory.getInstance().getSalePointInventoryDao();
                    break;
                default:
                    throw new KTMException("Err_no_InventoryType");
            }
        }
        return inventoryDao;
    }

    private Inventory getInventory(InventoryBean bean) throws KTMException {
        Inventory inventory = new Inventory();
        if (bean != null) {
            EInventoryType type = EInventoryType.parse(bean.getSelectedInventoryType());
            switch (type) {
                case CENTER:
                    inventory = new CenterInventory();
                    break;
                case MOVING:
                    inventory = new VehicleInventory();
                    break;
                case SALE_POINT:
                    inventory = new SalePointInventory();
                    break;
                default:
                    throw new KTMException("Err_no_InventoryType");
            }
        }
        return inventory;
    }

    public ActionForward saveInventory(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, KTMException {
        InventoryBean bean = (InventoryBean) form;

        InventoryDao inventoryDao = getInventoryDao(bean);
        Inventory inventory = getInventory(bean);

        if (!bean.getUniqueId().isEmpty()) {
            int id = Integer.valueOf(bean.getUniqueId());
            inventory = (Inventory) inventoryDao.get(id);
            if (inventory != null) {
                bean.syncToEntity(inventory);
            }
        } else {
            bean.syncToEntity(inventory);
        }
        inventoryDao.createOrUpdate(inventory);

        return ActionForward.getAction(this, request, "CRUDInventory?method=list", true);
    }

    public ActionForward editInventory(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, KTMException {
        InventoryBean bean = (InventoryBean) form;

        InventoryDao inventoryDao = KTMEMDaoFactory.getInstance().getInventoryDao();

        int id = Integer.valueOf(bean.getUniqueId());
        Inventory inventory = (Inventory) inventoryDao.get(id);
        if (inventory != null) {
            bean.loadToForm(inventory);
        }

        if (inventory instanceof CenterInventory) {
            bean.setSelectedInventoryType(EInventoryType.CENTER.toString());
        } else if (inventory instanceof SalePointInventory) {
            bean.setSelectedInventoryType(EInventoryType.SALE_POINT.toString());
        } else if (inventory instanceof VehicleInventory) {
            bean.setSelectedInventoryType(EInventoryType.MOVING.toString());
        }

        return ActionForward.getUri(this, request, "database/EditInventory.jsp");
    }

    public ActionForward delInventory(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, KTMException {
        InventoryBean bean = (InventoryBean) form;

        InventoryDao inventoryDao = KTMEMDaoFactory.getInstance().getInventoryDao();

        int id = Integer.valueOf(bean.getUniqueId());
        Inventory inventory = (Inventory) inventoryDao.get(id);
        if (inventory != null) {
            inventoryDao.delete(inventory);
        }

        return ActionForward.getAction(this, request, "CRUDInventory?method=list", true);
    }

}
