package service;

import org.springframework.beans.factory.annotation.Autowired;

import entity.TokenEntity;
import repository.TokenRepository;

public class TokenServiceImpl implements TokenService {
	@Autowired
	private TokenRepository tokenRepository;
	
	@Override
	public TokenEntity createToken(TokenEntity token) {
		// TODO Auto-generated method stub
		return tokenRepository.saveAndFlush(token);
	}

	@Override
	public TokenEntity findByToken(String token) {
		// TODO Auto-generated method stub
		return tokenRepository.findByToken(token);
	}

}
