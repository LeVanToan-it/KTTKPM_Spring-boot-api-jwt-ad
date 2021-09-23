package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import authen.UserPrincipal;
import entity.TokenEntity;
import entity.UserEntity;
import service.TokenService;
import service.UserService;
import util.JwtUtil;

@RestController
public class AuthController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/register")
	public UserEntity register(@RequestBody UserEntity user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
	
		return userService.createUser(user);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserEntity user) {
		UserPrincipal userPrincipal = userService.findByUsername(user.getUsername());
		
		if(null != user || !new BCryptPasswordEncoder()
				.matches(user.getPassword(), userPrincipal.getPassword())) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Account or password is not valid!");
				}
		
		TokenEntity token = new TokenEntity();
		token.setToken(jwtUtil.generateToken(userPrincipal));
		
		token.setTokenExpDate(jwtUtil.generateExpirationDate());
		token.setCreatedBy(userPrincipal.getUserID());
		tokenService.createToken(token);
		
		return ResponseEntity.ok(token.getToken());
	}
	
	@GetMapping("/hello")
	@PreAuthorize("hasAnyAuthority('USER_READ')")
	public ResponseEntity hello() {
		return ResponseEntity.ok("hello");
	}
}
