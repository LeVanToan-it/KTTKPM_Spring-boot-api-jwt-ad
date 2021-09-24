package com.springbootjwtauthen.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="t_role")
@Getter
@Setter
public class RoleEntity extends BaseEntity {
	@Column(name="rolename")
	private String roleName;
	
	@Column(name="rolekey")
	private String roleKey;
	
	@OneToMany
	@JoinTable(name="t_role_permission", joinColumns= {@JoinColumn(name="role_id")}, inverseJoinColumns= {@JoinColumn(name="permission_id")})
	private Set<PermissionEntity> permissions = new HashSet<>();

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleKey() {
		return roleKey;
	}

	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}

	public Set<PermissionEntity> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<PermissionEntity> permissions) {
		this.permissions = permissions;
	}


}
