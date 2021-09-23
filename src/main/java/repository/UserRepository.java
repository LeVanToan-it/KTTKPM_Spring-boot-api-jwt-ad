package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findByUsername(String username);
}
