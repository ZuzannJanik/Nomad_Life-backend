package com.crud.nomad.domain.dto;

import com.crud.nomad.domain.enums.VacType;;
import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VaccinationDto {
    private Long vacId;
    private String diseaseName;
    private LocalDate lastVac;
    private VacType vacType;
    private Long userId;
}
