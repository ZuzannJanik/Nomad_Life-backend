package com.crud.nomad.config;

import org.springframework.beans.factory.annotation.Value;

public class CountryConfig {
    @Value("${restCountries.api.url}")
    private String apiUrl;

    @Value("${restCountries.api.key}")
    private String apiKey;

    @Value("${restCountries.api.host}")
    private String apiHost;
}
