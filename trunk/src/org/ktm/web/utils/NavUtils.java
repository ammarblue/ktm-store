package org.ktm.web.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class NavUtils {

    private NavType                           currentMenu;
    private Map<NavType, String>              links = new LinkedHashMap<NavType, String>();
    private Map<NavType, Map<String, String>> menus = new LinkedHashMap<NavType, Map<String, String>>();

    public NavUtils() {
        currentMenu = NavType.edit;
        links.put(NavType.file, "nav.file");
        links.put(NavType.edit, "nav.edit");
        links.put(NavType.database, "nav.database");
        links.put(NavType.transaction, "nav.transaction");
        links.put(NavType.other, "nav.other");
        links.put(NavType.report, "nav.report");

        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("index.action?t=t&page=file-setting", "nav.file.setting");
        menus.put(NavType.file, map);

        map = new LinkedHashMap<String, String>();
        map.put("index.action?t=t&page=edit-cancel-bill", "nav.edit.cancel_bill");
        map.put("index.action?t=t&page=edit-cancel-sold", "nav.edit.cancel_sold");
        menus.put(NavType.edit, map);

        map = new LinkedHashMap<String, String>();
        map.put("index.action?t=t&page=database-user", "nav.database.user");
        map.put("index.action?t=t&page=database-product", "nav.database.product");
        map.put("index.action?t=t&page=database-group-product", "nav.database.group_product");
        map.put("index.action?t=t&page=database-supplier", "nav.database.supplier");
        map.put("index.action?t=t&page=database-customer", "nav.database.customer");
        map.put("index.action?t=t&page=database-vehicle-sale", "nav.database.substore");
        menus.put(NavType.database, map);

        map = new LinkedHashMap<String, String>();
        map.put("index.action?t=t&page=transaction-receive-from-supplier", "nav.transaction.receive_from_supplier");
        map.put("index.action?t=t&page=transaction-return-to-supplier", "nav.transaction.return_to_supplier");
        map.put("index.action?t=t&page=transaction-transfer", "nav.transaction.transfer");
        map.put("index.action?t=t&page=transaction-sale", "nav.transaction.sale");
        map.put("index.action?t=t&page=transaction-supplier-close-sale", "nav.transaction.supplier_close_sale");
        map.put("index.action?t=t&page=transaction-supplier-close-return", "nav.transaction.supplier_close_return");
        map.put("index.action?t=t&page=transaction-substore-close", "nav.transaction.substore_close");
        map.put("index.action?t=t&page=transaction-substore-return-close", "nav.transaction.substore_return_close");
        map.put("index.action?t=t&page=transaction-close-sale", "nav.transaction.close_sale");
        menus.put(NavType.transaction, map);

        map = new LinkedHashMap<String, String>();
        map.put("index.action?t=t&page=other-user-authrized", "nav.other.user_authrized");
        map.put("index.action?t=t&page=other-change-password", "nav.other.change_password");
        map.put("index.action?t=t&page=other-user-authen", "nav.other.user_authentication");
        menus.put(NavType.other, map);

        map = new LinkedHashMap<String, String>();
        map.put("index.action?t=t&page=report-before-close", "nav.report.before_close");
        map.put("index.action?t=t&page=report-store-balance", "nav.report.store_balance");
        map.put("index.action?t=t&page=report-all", "nav.report.all");
        map.put("index.action?t=t&page=report-after-close01", "nav.report.after_close01");
        map.put("index.action?t=t&page=report-after-close02", "nav.report.after_close02");
        map.put("index.action?t=t&page=report-after-close03", "nav.report.after_close03");
        map.put("index.action?t=t&page=report-after-close04", "nav.report.after_close04");
        map.put("index.action?t=t&page=report-after-close05", "nav.report.after_close05");
        map.put("index.action?t=t&page=report-after-close06", "nav.report.after_close06");
        map.put("index.action?t=t&page=report-after-close07", "nav.report.after_close07");
        menus.put(NavType.report, map);
    }

    public Map<NavType, String> getLinks() {
        return links;
    }

    public Map<String, String> getMenus() {
        return menus.get(currentMenu);
    }

    public void setCurrentMenu(NavType currentMenu) {
        this.currentMenu = currentMenu;
    }
}
