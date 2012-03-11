package org.ktm.web.manager;

import java.util.ArrayList;
import java.util.List;
import org.ktm.web.form.FrmDomain;

public abstract class FrmManagerAbstractImpl implements FormManager {

    public List<?> getSubList(List<?> cols, int form, int to) {
        return cols.subList(form, to);
    }

    public List<?> findNotById(List<?> cols, int id, int from, int to) {
        List<FrmDomain> sResult = new ArrayList<FrmDomain>();

        for (Object obj : cols) {
            Integer frmId = Integer.parseInt(((FrmDomain) obj).getId());
            if (obj instanceof FrmDomain && frmId != id) {
                sResult.add((FrmDomain) obj);
            }
        }

        return sResult.subList(from, to);
    }

    public List<?> findGreaterAsId(List<?> list, int id, int from, int to) {
        List<FrmDomain> sResult = new ArrayList<FrmDomain>();

        for (Object obj : list) {
            Integer frmId = Integer.parseInt(((FrmDomain) obj).getId());
            if (obj instanceof FrmDomain && frmId > id) {
                sResult.add((FrmDomain) obj);
            }
        }

        return sResult.subList(from, to);
    }

    public List<?> findLesserAsId(List<?> list, int id, int from, int to) {
        List<FrmDomain> sResult = new ArrayList<FrmDomain>();

        for (Object obj : list) {
            Integer frmId = Integer.parseInt(((FrmDomain) obj).getId());
            if (obj instanceof FrmDomain && frmId < id) {
                sResult.add((FrmDomain) obj);
            }
        }

        return sResult.subList(from, to);
    }
}
