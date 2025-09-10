package com.ram.renew_service.api.users.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ram.renew_service.api.common.exception.ApplicationException;
import com.ram.renew_service.api.config.JwtTokenProvider;
import com.ram.renew_service.api.users.repo.UserLoginLogRepository;
import com.ram.renew_service.api.users.repo.UsersRepository;
import com.ram.renew_service.entity.user.LogInRequest;
import com.ram.renew_service.entity.user.UserLogModel;
import com.ram.renew_service.entity.user.Users;


@Service
public class LoginService {
	@Autowired
	private AuthenticationManager authenticationManager;
	private final UsersRepository userRepo;
	private final UserLoginLogRepository userLogInRepo;
	private final JwtTokenProvider tokenProvider;
	
	
	
	
	public LoginService(UsersRepository userRepo,JwtTokenProvider tokenProvider,UserLoginLogRepository userLogInRepo) {
		super();
		this.userRepo = userRepo;
		this. tokenProvider= tokenProvider;
		this. userLogInRepo= userLogInRepo;
	}






	public Users loginUserVerification( LogInRequest loginRequest) {

		// Validate input parameters
	    if (loginRequest == null ) {
	        throw new ApplicationException("Error", "invalid Input Parameters", 
	               HttpStatus.BAD_REQUEST);
	    }
	    
	  
	    
	   
	   

	    if (loginRequest.getUserName().isEmpty() || loginRequest.getPassword().isEmpty()) {
	        throw new ApplicationException("Error", "User name or Password Empty",
	               HttpStatus.BAD_REQUEST);
	    }
	    // Find user
	    Users user;
	    try {
	        user = userRepo.findByUsernameAndStatus(loginRequest.getUserName(), 1);
	    } catch (Exception e) {
	       
	        throw new ApplicationException("Error", "SorryCouldnotLoginAtThisTime",
	               HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    if (user == null) {
	       
	        throw new ApplicationException("Error","UserNotFound",
		               HttpStatus.INTERNAL_SERVER_ERROR);
	    }

		Authentication authentication = null;
		try {
			authentication = getAuthentication(loginRequest.getUserName(), loginRequest.getPassword());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//log.error("Authentication failed for user: " + userName, e);
			
			throw new ApplicationException("401", "BadCredentials",  HttpStatus.UNAUTHORIZED);
			// return 111;
		}

		// Generate JWT token
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateToken(authentication, loginRequest.getUserName());

	
	     return saveLogInUser(user, jwt, loginRequest.getIpAddress());
	   
	}
	
	
	public Authentication getAuthentication(String userName, String passWord) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userName, passWord));
		return authentication;

	}
	
	
	
	public int checkPreviousUserLogDtls(int userId) {

		try {
			List<UserLogModel> lst = userLogInRepo.findActiveUserLog(userId);
			if (lst == null)
				return 0;

			return lst.size();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public Users saveLogInUser(Users user , String jwt, String loginIp) {

		try {
			int previousLogInDetails = checkPreviousUserLogDtls(user.getUserId());

			if (previousLogInDetails > 0) {

				throw new ApplicationException("Error", "101", HttpStatus.INTERNAL_SERVER_ERROR);

			}
			
			

			UserLogModel userLoginLog = new UserLogModel();
			userLoginLog.setAppsDateAD(new Date());
			userLoginLog.setAppsDateBS("");
			

			userLoginLog.setUserId(user.getUserId());
			userLoginLog.setLogInTime(new Date());
			userLoginLog.setLoginIp(loginIp);
			userLoginLog.setVersion("1.0");
			
			userLoginLog = userLogInRepo.save(userLoginLog);
			
			return user;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ApplicationException("Error", "Sorry Could not Login At This Time",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		
		

	}


}
