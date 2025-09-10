package com.ram.renew_service.entity.vehicle;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_insurance")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String insuranceName;
    private int insuranceType;
    private String insuranceAmount;
    private String policyNo;

    @Column(name = "from_date_ad")
    private LocalDate fromDateAd;

    @Column(name = "to_date_ad")
    private LocalDate toDateAd;

    @Column(name = "from_date_bs")
    private String fromDateBs;

    @Column(name = "to_date_bs")
    private String toDateBs;

    @OneToOne
    @JoinColumn(name = "veh_id")
    private Vehicle vehicle;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "updated_by")
    private Integer updatedBy;
}