package org.ktm.domain;

/*
 * The UniqueIdentifier represents an identifier that is unique within a given context.
 */
public abstract class UniqueIdentifier extends KTMEntity implements Comparable<UniqueIdentifier> {

	private static final long serialVersionUID = 1L;

	public abstract String getIdentifier();
	public abstract void setIdentifier(String identifier);
	
	public int compareTo(UniqueIdentifier o) {
		return getIdentifier().compareTo(o.getIdentifier());
	}
	
	@Override
	public boolean equals(Object other) {
		boolean result = false;
		if (other instanceof UniqueIdentifier) {
			UniqueIdentifier id = (UniqueIdentifier) other;
			if (id!=null && 
				id.getUniqueId() != null &&
				id.getIdentifier() != null &&
				this.getUniqueId() != null &&
				this.getIdentifier() != null)
			{
				if (this.getUniqueId()==id.getUniqueId() &&
					this.getIdentifier().equals(id.getIdentifier())) 
				{
					result = true;
				}
			}
		}
		return result;
	}
}
