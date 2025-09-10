package com.ram.renew_service.api.vehicle.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ram.renew_service.api.vehicle.service.InsuranceService;
import com.ram.renew_service.entity.vehicle.ClientInsuranceDTO;

@RestController
@RequestMapping("/insurance")
public class InsuranceRestController {


	    @Autowired
	    private InsuranceService insuranceService;

	    @PostMapping
	    public ClientInsuranceDTO createInsurance(@RequestBody ClientInsuranceDTO dto) {
	        return insuranceService.createInsurance(dto);
	    }

	    @GetMapping("/{id}")
	    public ClientInsuranceDTO getInsuranceById(@PathVariable int id) {
	        return insuranceService.getInsuranceById(id);
	    }

	    @GetMapping
	    public List<ClientInsuranceDTO> getAllInsurance() {
	        return insuranceService.getAllInsurance();
	    }

	    @PutMapping("/{id}")
	    public ClientInsuranceDTO updateInsurance(@PathVariable int id, @RequestBody ClientInsuranceDTO dto) {
	        return insuranceService.updateInsurance(id, dto);
	    }

	    @DeleteMapping("/{id}")
	    public void deleteInsurance(@PathVariable int id) {
	        insuranceService.deleteInsurance(id);
	    }
	}