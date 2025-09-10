package com.ram.renew_service.api.vehicle.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ram.renew_service.api.users.service.UserService;
import com.ram.renew_service.api.vehicle.repo.VehicleRepository;
import com.ram.renew_service.entity.user.Users;
import com.ram.renew_service.entity.vehicle.Vehicle;

@Service
public class VehicleService {

	private final VehicleRepository vehicleRepository;
	private final UserService userService;
	
	

	public VehicleService(VehicleRepository vehicleRepository, UserService userService) {
		super();
		this.vehicleRepository = vehicleRepository;
		this.userService = userService;
	}

	public Vehicle createVehicle(Vehicle vehicle, Long ownerId) {
		
		if (vehicleRepository.existsByPlateNumber(vehicle.getPlateNumber())) {
			throw new RuntimeException("Vehicle with this plate number already exists");
		}

		if (vehicleRepository.existsByChassisNumber(vehicle.getChassisNumber())) {
			throw new RuntimeException("Vehicle with this chassis number already exists");
		}

		if (vehicleRepository.existsByEngineNumber(vehicle.getEngineNumber())) {
			throw new RuntimeException("Vehicle with this engine number already exists");
		}

		return vehicleRepository.save(vehicle);
	}

	public List<Vehicle> getAllVehicles() {
		return vehicleRepository.findAll();
	}

	public Optional<Vehicle> getVehicleById(Long id) {
		return vehicleRepository.findById(id);
	}

	public List<Vehicle> getVehiclesByOwner(Long ownerId) {
		return vehicleRepository.findByOwnerId(ownerId);
	}

	public Vehicle updateVehicle(Long id, Vehicle vehicleDetails) {
		Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new RuntimeException("Vehicle not found"));

		vehicle.setMake(vehicleDetails.getMake());
		vehicle.setModel(vehicleDetails.getModel());
		vehicle.setYear(vehicleDetails.getYear());
		vehicle.setColor(vehicleDetails.getColor());
		vehicle.setVehicleType(vehicleDetails.getVehicleType());
		vehicle.setInsuranceExpiry(vehicleDetails.getInsuranceExpiry());

		return vehicleRepository.save(vehicle);
	}

	public void deleteVehicle(Long id) {
		Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new RuntimeException("Vehicle not found"));
		vehicleRepository.delete(vehicle);
	}

	public List<Vehicle> getVehiclesWithInsuranceExpiringSoon(int days) {
		LocalDate expiryDate = LocalDate.now().plusDays(days);
		return vehicleRepository.findVehiclesWithInsuranceExpiringSoon(expiryDate);
	}

	public Optional<Vehicle> getVehicleByPlateNumber(String plateNumber) {
		return vehicleRepository.findByPlateNumber(plateNumber);
	}
}
