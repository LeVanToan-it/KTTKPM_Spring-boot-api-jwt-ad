package com.springbootjwtauthen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootjwtauthen.entity.TokenEntity;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
	TokenEntity findByToken(String token);
}
