package com.springbootjwtauthen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootjwtauthen.entity.TokenEntity;
import com.springbootjwtauthen.repository.TokenRepository;

@Service
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
