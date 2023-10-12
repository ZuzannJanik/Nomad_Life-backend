package com.crud.nomad.domain;

import com.crud.nomad.domain.enums.MedType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "medicines")
@Entity
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "medicine_id")
    private Long medicineId;

    @Column(name = "medicine_name")
    private String medicineName;

    @Column(name = "designation")
    private String designation;

    @Column(name = "medicine_type")
    private MedType medType;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;
}
