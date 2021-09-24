package com.springbootjwtauthen.service;

import com.springbootjwtauthen.entity.TokenEntity;

public interface TokenService {
	TokenEntity createToken(TokenEntity token);
	TokenEntity findByToken(String token);
}
