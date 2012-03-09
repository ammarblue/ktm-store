package org.ktm.domain;

import java.io.Serializable;

public interface KTMEntityID extends Serializable {
	public Integer getUniqueId();
	public void setUniqueId(Integer uniqueId);
	public Integer getVersion() ;
	public void setVersion(Integer version);
}
