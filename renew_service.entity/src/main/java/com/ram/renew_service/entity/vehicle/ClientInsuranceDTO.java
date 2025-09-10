package com.ram.renew_service.entity.vehicle;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ClientInsuranceDTO {
	private int id;
    private Long clientId;
    private String insuranceCompany;
    private String policyNumber;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double premiumAmount;
    private int insuranceType;
}
