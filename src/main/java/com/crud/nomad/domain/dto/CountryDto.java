package com.crud.nomad.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryDto {

    @JsonProperty("countryId")
    private Long countryId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("flagUrl")
    private String flagUrl;
}
