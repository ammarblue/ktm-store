package org.ktm.domain;

public abstract class KTMEntity implements KTMEntityID {

	private static final long serialVersionUID = 1L;
	
	public KTMEntity() {
		
	}

	@Override
	public boolean equals(Object other) {
		boolean result = false;
		if (other instanceof KTMEntity) {
			KTMEntity party = (KTMEntity) other;
			if (party!=null && 
				party.getUniqueId() != null &&
				this.getUniqueId() != null)
			{
				if (this.getUniqueId()==party.getUniqueId()) {
					result = true;
				}
			}
		}
		return result;
	}
}
