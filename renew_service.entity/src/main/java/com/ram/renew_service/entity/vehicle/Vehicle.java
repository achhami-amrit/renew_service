package com.ram.renew_service.entity.vehicle;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "vehicles")
public class Vehicle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String plateNumber;
    
    @Column(nullable = false)
    private String make;// brand or manufacturer
    
    @Column(nullable = false)
    private String model;
    
    @Column(nullable = false)
    private Integer year;
    
    @Column(nullable = false)
    private String color;
    
    @Column(nullable = false)
    private String chassisNumber;
    
    @Column(nullable = false)
    private String engineNumber;
    
    @Column(nullable = false)
    private String vehicleType; // Car, Truck, Motorcycle, etc.
    
    @Column(nullable = false)
    private LocalDate registrationDateAd;
    
    @Column(nullable = false)
    private String registrationDateBs;
    
    @Column(nullable = false)
    private LocalDate insuranceExpiry;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;
    
   
}