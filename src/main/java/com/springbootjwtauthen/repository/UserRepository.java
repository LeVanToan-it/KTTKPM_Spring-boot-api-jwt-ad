package com.springbootjwtauthen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootjwtauthen.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findByUsername(String username);
}