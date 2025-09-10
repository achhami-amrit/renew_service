package com.ram.renew_service.api.users.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ram.renew_service.entity.user.Users;

public interface UsersRepository  extends JpaRepository<Users, Integer> {
	
	Users findByUsername(String username);
	Users findByUsernameAndPassword(String username, String password);
	@Query(value = "SELECT * FROM users u  WHERE u.user_name = :username  AND u.status = :status ", nativeQuery = true)
	Users findByUsernameAndStatus(@Param("username") String username,
	                                               @Param("status") int status);
	
	Optional<Users> findByEmail(String email);
    //Optional<Users> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

}
