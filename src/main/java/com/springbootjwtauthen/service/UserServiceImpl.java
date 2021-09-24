package com.springbootjwtauthen.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootjwtauthen.authen.UserPrincipal;
import com.springbootjwtauthen.entity.UserEntity;
import com.springbootjwtauthen.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserEntity createUser(UserEntity user) {
		// TODO Auto-generated method stub
		return userRepository.saveAndFlush(user);
	}

	@Override
	public UserPrincipal findByUsername(String username) {
		UserEntity user = userRepository.findByUsername(username);
		UserPrincipal userPrincipal = new UserPrincipal();
		
		if(null != user) {
			Set<String> authorities = new HashSet<>();
			
			if(null != user.getRoles()) {
				user.getRoles().forEach(
						r -> {
							authorities.add(r.getRoleKey());
							r.getPermissions().forEach(
									p -> authorities.add(p.getPermissionKey()));
						});
			}
			userPrincipal.setUserID(user.getId());
			userPrincipal.setUsername(user.getUsername());
			userPrincipal.setPassword(user.getPassword());
			userPrincipal.setAuthorities(authorities);
		}
		
		
		return userPrincipal;
	}

}
