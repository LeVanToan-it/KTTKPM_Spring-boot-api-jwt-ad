package entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="t_permission")
@Getter
@Setter
public class PermissionEntity extends BaseEntity {
	private String permissionName;
	private String permissionKey;
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	public String getPermissionKey() {
		return permissionKey;
	}
	public void setPermissionKey(String permissionKey) {
		this.permissionKey = permissionKey;
	}
	
}
