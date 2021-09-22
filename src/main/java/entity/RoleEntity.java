package entity;

import java.util.HashSet;
import java.util.Set;

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
	private String roleName;
	private String roleKey;
	
	@OneToMany
	@JoinTable(name="t_role_permission", joinColumns= {@JoinColumn(name="role_id")}, inverseJoinColumns= {@JoinColumn(name="permission_id")})
	private Set<PermissionEntity> permissions = new HashSet<>();
}
