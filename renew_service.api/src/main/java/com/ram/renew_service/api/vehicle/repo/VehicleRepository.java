package com.ram.renew_service.api.vehicle.repo;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ram.renew_service.entity.user.Users;
import com.ram.renew_service.entity.vehicle.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
	Optional<Vehicle> findByPlateNumber(String plateNumber);
    //List<Vehicle> findByOwner(Users owner);
    List<Vehicle> findByVehicleType(String vehicleType);
    boolean existsByPlateNumber(String plateNumber);
    boolean existsByChassisNumber(String chassisNumber);
    boolean existsByEngineNumber(String engineNumber);
    
    @Query("SELECT v FROM Vehicle v WHERE v.insuranceExpiry BETWEEN CURRENT_DATE AND :date")
    List<Vehicle> findVehiclesWithInsuranceExpiringSoon(@Param("date") LocalDate date);
    
    @Query("SELECT v FROM Vehicle v WHERE v.client.clientId = :ownerId")
    List<Vehicle> findByOwnerId(Long ownerId);
}
