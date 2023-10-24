package com.crud.nomad.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountryDto {
    private Long countryId;
    private String countryName;
    private String flagUrl;
}
