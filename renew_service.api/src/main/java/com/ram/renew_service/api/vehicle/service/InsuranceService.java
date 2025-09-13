package com.ram.renew_service.api.vehicle.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ram.renew_service.api.vehicle.repo.InsuranceRepository;
import com.ram.renew_service.entity.vehicle.ClientInsuranceDTO;
import com.ram.renew_service.entity.vehicle.Insurance;

@Service
public class InsuranceService {
	@Autowired
    private InsuranceRepository insuranceRepository;

    private ClientInsuranceDTO convertToDTO(Insurance insurance) {
    	ClientInsuranceDTO dto = new ClientInsuranceDTO();
        dto.setId(insurance.getId());
        dto.setPolicyNumber(insurance.getPolicyNo());
        dto.setInsuranceType(insurance.getInsuranceType());
        dto.setStartDate(insurance.getFromDateAd());
        dto.setEndDate(insurance.getToDateAd());
        return dto;
    }

    private Insurance convertToEntity(ClientInsuranceDTO dto) {
        Insurance insurance = new Insurance();
        insurance.setId(dto.getId());
        insurance.setPolicyNo(dto.getPolicyNumber());
        insurance.setInsuranceType(dto.getInsuranceType());
        insurance.setFromDateAd(dto.getStartDate());
        insurance.setToDateAd(dto.getEndDate());
        return insurance;
    }

    public ClientInsuranceDTO createInsurance(ClientInsuranceDTO dto) {
        Insurance insurance = convertToEntity(dto);
        return convertToDTO(insuranceRepository.save(insurance));
    }

    public ClientInsuranceDTO getInsuranceById(int id) {
        return insuranceRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    
    public List<ClientInsuranceDTO> getAllInsurance() {
        return insuranceRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ClientInsuranceDTO updateInsurance(int id, ClientInsuranceDTO dto) {
        return insuranceRepository.findById(id)
                .map(existing -> {
                    existing.setPolicyNo(dto.getPolicyNumber());
                    existing.setInsuranceType(dto.getInsuranceType());
                    existing.setFromDateAd(dto.getStartDate());
                    existing.setToDateAd(dto.getEndDate());
                    return convertToDTO(insuranceRepository.save(existing));
                }).orElse(null);
    }

   
    public void deleteInsurance(int id) {
        insuranceRepository.deleteById(id);
    }
}
