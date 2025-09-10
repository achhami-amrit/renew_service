package com.ram.renew_service.api.vehicle.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ram.renew_service.entity.vehicle.Insurance;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Integer> {
	
}