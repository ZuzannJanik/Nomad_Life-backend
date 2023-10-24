package com.crud.nomad.domain.dto;

import com.crud.nomad.domain.NomadUser;
import com.crud.nomad.domain.enums.TripStatus;
import lombok.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TripDto {
    private Long tripId;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private String destinationCountry;
    private TripStatus tripStatus;
    @Builder.Default
    private Set<NomadUser> nomadUserList = new HashSet<>();
}
