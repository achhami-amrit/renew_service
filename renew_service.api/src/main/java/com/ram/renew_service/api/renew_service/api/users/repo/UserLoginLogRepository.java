package com.ram.renew_service.api.renew_service.api.users.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ram.renew_service.entity.renew_service.entity.user.UserLogModel;

public interface UserLoginLogRepository  extends JpaRepository<UserLogModel, Integer>{
	
	
	@Query("SELECT d FROM UserLogModel d where d.logOutTime IS Null and d.userId = :userId")
	List<UserLogModel> findActiveUserLog(@Param("userId") int userId);

}
