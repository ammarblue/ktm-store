package org.ktm.domain.party;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.ktm.domain.KTMEntity;

/*
 * The RoleSet specifics a PartyRole or PartyRelationship name for
 * PartyRoleType and PartyRelationshipType 
 */
@Entity
@Table(name="role_set")
public class RoleSet extends KTMEntity {

	private static final long serialVersionUID = 1L;

	private Integer uniqueId;
	private Integer version;
	private String roleName;

    @Id
    @GeneratedValue
    @Column(name = "uniqueId", nullable = false)
	public Integer getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(Integer uniqueId) {
		this.uniqueId = uniqueId;
	}
	@Version
    @Column(name="version")
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	@Column(name="roleName")
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
