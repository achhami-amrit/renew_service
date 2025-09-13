package com.ram.renew_service.entity.vehicle;

import lombok.Data;

@Data
public class ClientDTO {
    private Long clientId;
    private String name;
    private String address;
    private String mobileNo;
    private String email;
    private String gender;
    private Integer noOfRenew;
}