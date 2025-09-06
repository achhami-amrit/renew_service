package com.ram.renew_service.entity.renew_service.entity.user;

import java.util.Collection;
import java.util.Date;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;


@Data
@Entity
@Table(name = "users")

public class Users implements UserDetails {
	private static final long serialVersionUID = 1L;

	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "useridgen", strategy = "increment")
	@GeneratedValue(generator = "useridgen")
	@Column(name = "user_id", nullable = false)
	private int userId;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "address")
	private String address;

	@Column(name = "contact_no")
	private String contactNo;

	@Column(name =  "gender")
	public int gender;

	@Column(name = "user_name", nullable = false, unique = true)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name =  "status")
	public int  status;

	@Column(name =  "role_id")
	private int role;

	@Column(name =  "designation")
	private int designation;


	
	
	@Column(name = "is_super_admin")
	private Boolean isSuperAdmin;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "pswd_change_date_ad")
	private Date pswdChangeDateAd;
	
	

	






	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}








	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}








	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}








	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}








	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}








	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}








	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	
	


	public Integer getId() {
		return userId;
	}

	public void setId(Integer id) {
		this.userId = id;
	}
	
	

}
