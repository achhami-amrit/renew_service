package com.ram.renew_service.entity.renew_service.entity.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LogInRequest {
	
	@NotBlank(message = "User name is required")
	private String userName;
	
	
	@NotBlank(message = "Password is required")
	private String password;
	
	
	@NotBlank(message = "ip address is required")
	private String ipAddress;

}
