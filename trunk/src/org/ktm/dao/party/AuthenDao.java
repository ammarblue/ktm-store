package org.ktm.dao.party;

import java.util.List;
import org.ktm.dao.Dao;
import org.ktm.domain.party.*;

public interface AuthenDao extends Dao {

	public Authen findByUsername(String username);
	public List<Authen> findByPartyId(Integer id);

}
