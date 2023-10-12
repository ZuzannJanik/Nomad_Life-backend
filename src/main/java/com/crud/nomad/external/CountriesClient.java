package com.crud.nomad.external;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Getter
public class CountriesClient {

    @Value("${restCountries.api.url}")
    private String apiUrl;

    @Value("${restCountries.api.key}")
    private String apiKey;

    @Value("${restCountries.api.host}")
    private String apiHost;

    private final RestTemplate restTemplate;

    public ResponseEntity<String> getCountries() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("x-rapidapi-key", apiKey);
            headers.set("x-rapidapi-host", apiHost);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            return restTemplate.exchange(apiUrl, HttpMethod.GET, new HttpEntity<>(headers), String.class);

        } catch (Exception e) {
          //  log.error("Something went wrong while getting value from RapidApi", e);
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Exception while caling endpoint of RapidAPI", e);
        }
    }
}
