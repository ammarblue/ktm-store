package org.ktm.stock.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.ktm.domain.KTMEntity;
import org.ktm.domain.order.PurchaseOrder;

public class PurchaseOrderBean extends OrderBean {

    private List<PurchaseOrderBean> purchaseOrderBeanCollection = new ArrayList<PurchaseOrderBean>();

    @Override
    public void loadFormCollection(Collection<?> entitys) {
        purchaseOrderBeanCollection.clear();
        if (entitys != null && entitys.size() > 0) {
            for (Object obj : entitys) {
                if (obj instanceof PurchaseOrder) {
                    PurchaseOrder entity = (PurchaseOrder) obj;
                    PurchaseOrderBean bean = new PurchaseOrderBean();
                    bean.loadToForm(entity);
                    purchaseOrderBeanCollection.add(bean);
                }
            }
        }
    }

    @Override
    public void loadToForm(KTMEntity entity) {
        if (entity != null) {
            if (entity instanceof PurchaseOrder) {
                super.loadToForm(entity);
            }
        }
    }

    public List<PurchaseOrderBean> getPurchaseOrderBeanCollection() {
        return purchaseOrderBeanCollection;
    }

    public void setPurchaseOrderBeanCollection(List<PurchaseOrderBean> purchaseOrderBeanCollection) {
        this.purchaseOrderBeanCollection = purchaseOrderBeanCollection;
    }
}
