package com.springbootjwtauthen.service;

import com.springbootjwtauthen.authen.UserPrincipal;
import com.springbootjwtauthen.entity.UserEntity;

public interface UserService {
	UserEntity createUser(UserEntity user);
	UserPrincipal findByUsername(String username);
}
