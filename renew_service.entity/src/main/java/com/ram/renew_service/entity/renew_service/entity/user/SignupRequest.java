package com.ram.renew_service.entity.renew_service.entity.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class SignupRequest {
	@NotBlank(message = "Username is required")
	private String username;

	@Email(message = "Invalid email address")
	private String email;

	@NotBlank(message = "Password is required")
	private String password;
	
	
	@NotBlank(message = "address is required")
	private String address;
	
	
	@NotBlank(message = "Full name is required")
	private String full_name;

}
