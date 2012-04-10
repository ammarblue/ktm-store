package org.ktm.web.manager;

import java.util.List;
import org.ktm.web.form.FrmOrder;
import org.ktm.web.form.FrmOrderLine;

public interface OrderManager extends FormManager {

    public List<FrmOrderLine> getOrderLine(FrmOrder frmOrder);
    
}
