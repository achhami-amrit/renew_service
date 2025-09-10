package com.ram.renew_service.api.users.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ram.renew_service.api.users.service.AuthService;
import com.ram.renew_service.api.users.service.LoginService;
import com.ram.renew_service.entity.user.LogInRequest;
import com.ram.renew_service.entity.user.SignupRequest;
import com.ram.renew_service.entity.user.Users;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")

public class AuthController {

	private AuthService userServiceController;
	private LoginService loginService;

	public AuthController(AuthService userServiceController,LoginService loginService) {
		super();
		this.userServiceController = userServiceController;
		this. loginService = loginService;
	}

	@PostMapping(value = "/signUp")
	public ResponseEntity<?> signUpUsers(@Valid @RequestBody SignupRequest request) {
		return userServiceController.signUpUsers(request);
	}
	
	
	
	@PostMapping(value = "/login")
	public Users loginVerification(@Valid @RequestBody LogInRequest loginRequest) {
		return loginService.loginUserVerification(loginRequest);
	}

}
