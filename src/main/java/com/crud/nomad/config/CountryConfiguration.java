package com.crud.nomad.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class CountryConfiguration {
    @Value("${restCountries.api.url}")
    private String countryUrl;

    @Value("${restCountries.api.key}")
    private String countryKey;

    @Value("${restCountries.api.host}")
    private String countryHost;
}
