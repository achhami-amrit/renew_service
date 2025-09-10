package com.ram.renew_service.api.users.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ram.renew_service.api.users.repo.UsersRepository;
import com.ram.renew_service.entity.user.Users;

@Service
public class UserService implements UserDetailsService {

    private final UsersRepository userRepository;

    public UserService(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        
        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities("ROLE_USER")
                .build();
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public Users saveUser(Users user) {
        return userRepository.save(user);
    }

    public Optional<Users> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}