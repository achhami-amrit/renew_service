package com.ram.renew_service.api.vehicle.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ram.renew_service.api.vehicle.service.VehicleService;
import com.ram.renew_service.entity.vehicle.Vehicle;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleRestController {
	private final VehicleService vehicleService;

	public VehicleRestController(VehicleService vehicleService) {
		super();
		this.vehicleService = vehicleService;
	}

	@PostMapping
	public ResponseEntity<?> createVehicle(@RequestBody Vehicle vehicle, @RequestParam Long ownerId) {
		try {
			Vehicle createdVehicle = vehicleService.createVehicle(vehicle, ownerId);
			return ResponseEntity.ok(createdVehicle);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
		}
	}

	public ResponseEntity<List<Vehicle>> getAllVehicles() {
		return ResponseEntity.ok(vehicleService.getAllVehicles());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getVehicleById(@PathVariable Long id) {
		return vehicleService.getVehicleById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/owner/{ownerId}")
	public ResponseEntity<List<Vehicle>> getVehiclesByOwner(@PathVariable Long ownerId) {
		return ResponseEntity.ok(vehicleService.getVehiclesByOwner(ownerId));
	}

	@GetMapping("/plate/{plateNumber}")
	public ResponseEntity<?> getVehicleByPlateNumber(@PathVariable String plateNumber) {
		return vehicleService.getVehicleByPlateNumber(plateNumber).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicleDetails) {
		try {
			Vehicle updatedVehicle = vehicleService.updateVehicle(id, vehicleDetails);
			return ResponseEntity.ok(updatedVehicle);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteVehicle(@PathVariable Long id) {
		try {
			vehicleService.deleteVehicle(id);
			return ResponseEntity.ok().body(Map.of("message", "Vehicle deleted successfully"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
		}
	}

	@GetMapping("/insurance/expiring")
	public ResponseEntity<List<Vehicle>> getVehiclesWithInsuranceExpiringSoon(
			@RequestParam(defaultValue = "30") int days) {
		return ResponseEntity.ok(vehicleService.getVehiclesWithInsuranceExpiringSoon(days));
	}
}
