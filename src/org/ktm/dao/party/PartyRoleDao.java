package org.ktm.dao.party;

import org.ktm.dao.Dao;
import org.ktm.domain.party.*;

public interface PartyRoleDao extends Dao {

	public PartyRole findByRoleName(Party party, String roleName);

}
