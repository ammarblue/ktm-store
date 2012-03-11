package org.ktm.web.manager;

import java.io.Serializable;
import java.util.List;
import org.ktm.actions.KTMAction;
import org.ktm.web.form.FrmDomain;

public interface FormManager {

    FrmDomain get(KTMAction action, Serializable tryId);

    List<FrmDomain> findAll(KTMAction action);

    Integer delete(KTMAction action, Serializable tryId);

    Integer delete(KTMAction action, FrmDomain toDelete);
    
    Integer addOrUpdate(KTMAction action, FrmDomain toAdd);

    public List<?> getSubList(List<?> cols, int form, int to);

    public List<?> findNotById(List<?> cols, int id, int from, int to);

    public List<?> findGreaterAsId(List<?> list, int id, int from, int to);

    public List<?> findLesserAsId(List<?> list, int id, int from, int to);
}
