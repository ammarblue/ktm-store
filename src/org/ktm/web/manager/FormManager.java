package org.ktm.web.manager;

import java.io.Serializable;
import java.util.List;
import org.ktm.web.form.FrmDomain;

public interface FormManager {

    public FrmDomain get(Serializable tryId);

    public List<FrmDomain> findAll();

    public Integer delete(Serializable tryId);

    public Integer delete(FrmDomain toDelete);
    
    public Integer addOrUpdate(FrmDomain toAdd);

    public List<?> getSubList(List<?> cols, int form, int to);

    public List<?> findNotById(List<?> cols, int id, int from, int to);

    public List<?> findGreaterAsId(List<?> list, int id, int from, int to);

    public List<?> findLesserAsId(List<?> list, int id, int from, int to);

}
