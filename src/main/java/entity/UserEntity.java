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
@Table(name="t_user")
@Getter
@Setter
public class UserEntity extends BaseEntity {
	private String username;
	private String password;
	
	@OneToMany
	@JoinTable(name="t_user_role", joinColumns= {@JoinColumn(name="user_id")},inverseJoinColumns= {@JoinColumn(name="role_id")})
	private Set<RoleEntity> roles = new HashSet<>();
}
