package com.crud.nomad.domain.dto;

import com.crud.nomad.domain.enums.MedType;
import lombok.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MedicineDto {
    private Long medicineId;
    private String medicineName;
    private String designation;
    private MedType medType;
    private LocalDate expiryDate;
}
