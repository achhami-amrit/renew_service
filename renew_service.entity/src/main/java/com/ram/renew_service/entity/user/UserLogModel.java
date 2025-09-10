package com.ram.renew_service.entity.user;

import java.util.Date;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "user_log_mcg")

public class UserLogModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	@GenericGenerator(name = "userLogIdGen", strategy = "increment")
//	@GeneratedValue(generator = "userLogIdGen")
	@Column(name = "user_log_id", nullable = false)
	private int userLogId;


	@Column(name = "user_id")
	private int userId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "log_in_time", nullable = false)
	private Date logInTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "log_out_time")
	private Date logOutTime;

	// OS Date
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "apps_date_ad", nullable = false)
	private Date appsDateAD;

	// OS Date
	@Column(name = "apps_date_bs", nullable = false)
	private String appsDateBS;

	@Column(name = "version", nullable = false)
	private String version;

	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "login_ip", nullable = false)
	private String loginIp;
	
	@Column(name = "logout_ip", nullable = false)
	private String logoutIp;

	
}
