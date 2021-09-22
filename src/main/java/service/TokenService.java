package service;

import entity.TokenEntity;

public interface TokenService {
	TokenEntity createToken(TokenEntity token);
	TokenEntity findByToken(String token);
}
