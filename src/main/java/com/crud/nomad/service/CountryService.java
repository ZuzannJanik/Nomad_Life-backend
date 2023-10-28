package com.crud.nomad.service;

import com.crud.nomad.config.CountryConfiguration;
import com.crud.nomad.domain.Country;
import com.crud.nomad.exceptions.CountryNotFoundException;
import com.crud.nomad.respository.CountryRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.http.HttpRequest;
import java.util.List;

@Service
@Slf4j
public class CountryService implements HTTPResponse{
    private final CountryRepository repository;
    private final CountryConfiguration countryConfiguration;
    @Autowired
    public CountryService(CountryRepository repository, CountryConfiguration countryConfiguration) {
        this.repository = repository;
        this.countryConfiguration = countryConfiguration;
    }
    public Country getCountryFlagURL(String countryName) throws CountryNotFoundException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(countryConfiguration.getCountryUrl() + countryName))
                .header("X-RapidAPI-Key", countryConfiguration.getCountryKey())
                .header("X-RapidAPI-Host", countryConfiguration.getCountryHost())
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        String responseBody = getResponseBody(request);
        String flagUrl = "";

        JsonElement jsonElement = JsonParser.parseString(responseBody);
        if (jsonElement.isJsonArray()) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();

            if (jsonArray.size() > 0) {
                JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();

                if (jsonObject.has("flag")) {
                    flagUrl = jsonObject.getAsJsonObject("flag").getAsJsonObject("officialflag").get("svg").getAsString();
                }
            } else {
                throw new CountryNotFoundException();
            }
        }
        return Country.builder()
                .countryName(countryName)
                .flagUrl(flagUrl)
                .build();
    }

      RestTemplate restTemplate = new RestTemplate();
    public List<Country> getAllCountries() {
        return repository.findAll();
    }
    public Country getCountry(final Long countryId) throws CountryNotFoundException {
        return repository.findById(countryId).orElseThrow(CountryNotFoundException::new);
    }
    public Country saveCountry(final Country country) {
        return repository.save(country);
    }
}
