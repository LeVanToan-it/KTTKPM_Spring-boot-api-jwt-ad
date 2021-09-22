package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.TokenEntity;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
	TokenEntity findByToken(String token);
}
