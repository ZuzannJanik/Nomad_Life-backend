package com.crud.nomad.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class GoogleSearchConfiguration {
    @Value("${googleSearch.api.url}")
    private String googleUrl;

    @Value("${googleSearch.api.key}")
    private String googleKey;

    @Value("${googleSearch.api.host}")
    private String googleHost;
}
