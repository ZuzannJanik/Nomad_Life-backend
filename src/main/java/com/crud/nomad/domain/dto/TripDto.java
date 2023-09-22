package com.crud.nomad.domain.dto;

import com.crud.nomad.domain.TripStatus;
import lombok.*;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class TripDto {
    private Long tripId;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private String destinationCountry;
    private TripStatus tripStatus;
}
