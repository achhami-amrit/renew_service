package com.ram.renew_service.entity.vehicle;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_renew_request")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RenewRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer renewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veh_id")
    private Vehicle vehicle;

    @Column(name = "request_date_ad")
    private LocalDate requestDateAd;

    @Column(name = "request_date_bs")
    private String requestDateBs;

    private String status;

    @OneToOne(mappedBy = "renewRequest", cascade = CascadeType.ALL)
    private Payment payment;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "updated_by")
    private Integer updatedBy;
}