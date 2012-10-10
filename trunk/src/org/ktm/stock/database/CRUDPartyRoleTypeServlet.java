package org.ktm.stock.database;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ktm.dao.party.PartyRoleTypeDao;
import org.ktm.domain.party.PartyRoleType;
import org.ktm.exception.CreateException;
import org.ktm.exception.DeleteException;
import org.ktm.servlet.ActionForward;
import org.ktm.servlet.CRUDServlet;
import org.ktm.stock.bean.PartyRoleTypeBean;
import org.ktm.stock.dao.KTMEMDaoFactory;
import org.ktm.web.bean.FormBean;

@WebServlet("/CRUDPartyRoleType")
public class CRUDPartyRoleTypeServlet extends CRUDServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public String getBeanClass() {
        return "org.ktm.stock.bean.PartyRoleTypeBean";
    }

    public ActionForward listPartyRoleType(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PartyRoleTypeDao partyRoleTypeDao = KTMEMDaoFactory.getInstance().getPartyRoleTypeDao();
        List<PartyRoleType> partyRoleTypes = partyRoleTypeDao.findAll();
        PartyRoleTypeBean bean = (PartyRoleTypeBean) form;
        bean.loadFormCollection(partyRoleTypes);
        return ActionForward.getUri(this, request, "database/ListAllPartyRoleType.jsp");
    }

    public ActionForward newPartyRoleType(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return ActionForward.getUri(this, request, "database/EditPartyRoleType.jsp");
    }

    public ActionForward savePartyRoleType(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CreateException {
        PartyRoleTypeBean bean = (PartyRoleTypeBean) form;
        PartyRoleTypeDao partyRoleTypeDao = KTMEMDaoFactory.getInstance().getPartyRoleTypeDao();
        PartyRoleType partyRoleType = new PartyRoleType();

        if (!bean.getUniqueId().isEmpty()) {
            Integer id = Integer.valueOf(bean.getUniqueId());
            partyRoleType = (PartyRoleType) partyRoleTypeDao.get(id);
            bean.syncToEntity(partyRoleType);

            partyRoleTypeDao.createOrUpdate(partyRoleType);
        } else {
            bean.syncToEntity(partyRoleType);

            if (partyRoleTypeDao.findByName(bean.getName()) == null) {
                partyRoleTypeDao.createOrUpdate(partyRoleType);
            }

        }

        return ActionForward.getAction(this, request, "CRUDPartyRoleType?method=list", true);
    }

    public ActionForward editPartyRoleType(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PartyRoleTypeBean bean = (PartyRoleTypeBean) form;

        PartyRoleTypeDao partyRoleTypeDao = KTMEMDaoFactory.getInstance().getPartyRoleTypeDao();

        int id = Integer.valueOf(bean.getUniqueId());
        PartyRoleType partyRoleType = (PartyRoleType) partyRoleTypeDao.get(id);
        if (partyRoleType != null) {
            bean.loadToForm(partyRoleType);
        }

        return ActionForward.getUri(this, request, "database/EditPartyRoleType.jsp");
    }

    public ActionForward delPartyRoleType(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DeleteException {
        PartyRoleTypeBean bean = (PartyRoleTypeBean) form;

        PartyRoleTypeDao partyRoleTypeDao = KTMEMDaoFactory.getInstance().getPartyRoleTypeDao();

        int id = Integer.valueOf(bean.getUniqueId());
        partyRoleTypeDao.delete(id);

        return ActionForward.getAction(this, request, "CRUDPartyRoleType?method=list", true);
    }
}
