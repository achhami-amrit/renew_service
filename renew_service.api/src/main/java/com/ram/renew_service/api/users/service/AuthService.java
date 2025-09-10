package com.ram.renew_service.api.users.service;

import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.ram.renew_service.api.common.exception.ApplicationException;
import com.ram.renew_service.api.users.repo.UsersRepository;
import com.ram.renew_service.entity.user.SignupRequest;
import com.ram.renew_service.entity.user.Users;


@Service

public class AuthService {
	
	private UsersRepository userRepo;
	private final PasswordEncoder passwordEncoder;
	
	

	public AuthService(UsersRepository userRepo, PasswordEncoder passwordEncoder) {
		super();
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}



	public ResponseEntity<?> signUpUsers(SignupRequest request) {
		try {
			Users user = new Users();
			user.setUsername(request.getUsername());
			user.setAddress(request.getAddress());
			user.setName(request.getFull_name());
			user.setDesignation(1);
			user.setIsSuperAdmin(false);
			
			
			user.setStatus(1);

			if (userRepo.findByUsername(request.getUsername()) != null) {
				throw new ApplicationException("Error", new StringBuffer().append("User name "+request.getUsername()+" already exists ").toString(),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			
			if (!PasswordValidator.isValid(request.getPassword())) {
	            return ResponseEntity.badRequest().body("Password is too weak. It must contain: " +
	                    "at least 8 characters, 1 uppercase, 1 lowercase, 1 number, and 1 special character.");
	        }

			user.setPassword(passwordEncoder.encode(request.getPassword()));

			
			user = userRepo.save(user);
			 return ResponseEntity.ok("User registered successfully!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (e instanceof ApplicationException ) {
				ApplicationException applicationExcp = (ApplicationException) e;
				
				throw new ApplicationException("Error", applicationExcp.getMessage(),
						HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
			 return ResponseEntity.internalServerError().body("Error while Signup process , Please try again ");
		}
	}
	
	
	
	
	public class PasswordValidator {

	    // Minimum 8 characters, at least one uppercase letter, one lowercase letter,
	    // one digit and one special character
	    private static final String PASSWORD_PATTERN =
	            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

	    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

	    public static boolean isValid(String password) {
	        return password != null && pattern.matcher(password).matches();
	    }}
	
	
	
	
	
	
	
	


}
