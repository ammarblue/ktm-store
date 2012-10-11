package org.ktm.stock.tags;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubMenu {

    private static HashMap<String, List<MenuItem>> menuMap = new HashMap<String, List<MenuItem>>();

    static {
        List<MenuItem> menus = new ArrayList<MenuItem>();
        menuMap.put("main", menus);
        menus.add(new MenuItem("nav.file.setting", "#"));

        menus = new ArrayList<MenuItem>();
        menuMap.put("edit", menus);
        menus.add(new MenuItem("nav.edit.cancel_bill", "#"));
        menus.add(new MenuItem("nav.edit.cancel_sold", "#"));

        menus = new ArrayList<MenuItem>();
        menuMap.put("database", menus);
        menus.add(new MenuItem("nav.database.party_role_type", "CRUDPartyRoleType?method=list"));
        menus.add(new MenuItem("nav.database.product", "CRUDProductType?method=list"));
        menus.add(new MenuItem("nav.database.group_product", "CRUDCatalogEntryType?method=list"));
        menus.add(new MenuItem("nav.database.supplier", "CRUDSupplier?method=list"));
        menus.add(new MenuItem("nav.database.customer", "CRUDCustomer?method=list"));
        menus.add(new MenuItem("nav.database.store", "CRUDSubstore?method=list"));
        menus.add(new MenuItem("nav.database.employee", "CRUDPerson?method=list"));

        menus = new ArrayList<MenuItem>();
        menuMap.put("transaction", menus);
        menus.add(new MenuItem("nav.transaction.receive_from_supplier", "#"));
        menus.add(new MenuItem("nav.transaction.return_to_supplier", "#"));
        menus.add(new MenuItem("nav.transaction.transfer", "#"));
        menus.add(new MenuItem("nav.transaction.sale", "#"));
        menus.add(new MenuItem("nav.transaction.supplier_close_sale", "#"));
        menus.add(new MenuItem("nav.transaction.supplier_close_return", "#"));
        menus.add(new MenuItem("nav.transaction.substore_close", "#"));
        menus.add(new MenuItem("nav.transaction.substore_return_close", "#"));
        menus.add(new MenuItem("nav.transaction.close_sale", "#"));

        menus = new ArrayList<MenuItem>();
        menuMap.put("other", menus);
        menus.add(new MenuItem("nav.other.user_authrized", "#"));
        menus.add(new MenuItem("nav.other.change_password", "#"));
        menus.add(new MenuItem("nav.other.user_authentication", "#"));
        menus.add(new MenuItem("nav.other.user_authrized", "#"));
        menus.add(new MenuItem("nav.other.user_authrized", "#"));

        menus = new ArrayList<MenuItem>();
        menuMap.put("report", menus);
        menus.add(new MenuItem("nav.report.before_close", "#"));
        menus.add(new MenuItem("nav.report.store_balance", "#"));
        menus.add(new MenuItem("nav.report.all", "#"));
        menus.add(new MenuItem("nav.report.after_close01", "#"));
        menus.add(new MenuItem("nav.report.after_close02", "#"));
        menus.add(new MenuItem("nav.report.after_close03", "#"));
        menus.add(new MenuItem("nav.report.after_close04", "#"));
        menus.add(new MenuItem("nav.report.after_close05", "#"));
        menus.add(new MenuItem("nav.report.after_close06", "#"));
        menus.add(new MenuItem("nav.report.after_close07", "#"));
    }

    public static List<MenuItem> getMenu(String page) {
        return menuMap.get(page);
    }
}
