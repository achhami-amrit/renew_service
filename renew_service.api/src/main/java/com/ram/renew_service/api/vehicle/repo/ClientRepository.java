package com.ram.renew_service.api.vehicle.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ram.renew_service.entity.vehicle.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	
}
