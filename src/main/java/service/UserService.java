package service;

import authen.UserPrincipal;
import entity.UserEntity;
import repository.UserRepository;

public interface UserService {
	UserEntity createUser(UserEntity user);
	UserPrincipal findByUsername(String username);
}
