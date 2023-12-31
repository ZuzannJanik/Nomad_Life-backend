package com.crud.nomad.domain;

import com.crud.nomad.domain.enums.VacType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "vaccinations")
@Entity
public class Vaccination {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vac_id")
    private Long vacId;

    @NotNull
    @Column(name = "disease")
    private String diseaseName;

    @Column(name = "last_vac")
    private LocalDate lastVac;

    @Column(name = "complete")
    private VacType vacType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private NomadUser nomadUser;

    public Vaccination(Long vacId, String diseaseName, LocalDate lastVac, VacType vacType) {
        this.vacId = vacId;
        this.diseaseName = diseaseName;
        this.lastVac = lastVac;
        this.vacType = vacType;
    }
}
