package com.crud.nomad.controller;

import com.crud.nomad.domain.Country;
import com.crud.nomad.domain.dto.CountryDto;
import com.crud.nomad.exceptions.CountryNotFoundException;
import com.crud.nomad.mapper.CountryMapper;
import com.crud.nomad.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@RestController
@RequestMapping("/api/v1/countries")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CountryController {
    private final CountryMapper countryMapper;
    private final CountryService service;

 /*   @GetMapping
    public ResponseEntity<List<CountryDto>> getCountries() {
        List<Country> countries = service.getAllCountries();
        return ResponseEntity.ok(countryMapper.mapToCountryDtoList(countries));
    }
    @GetMapping(value = "{countryId}")
    public ResponseEntity<?> getCountry(@PathVariable Long countryId) throws CountryNotFoundException {
        return ResponseEntity.ok(countryMapper.mapToCountryDto(service.getCountry(countryId)));
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createTrip(@RequestBody CountryDto countryDto) {
        Country country = countryMapper.mapToCountry(countryDto);
        service.saveCountry(country);
        return ResponseEntity.ok().build();
    }
 */   @GetMapping(value = "{countryName}")
    ResponseEntity<?> getCountryFlagURL(@PathVariable String countryName) throws IOException, InterruptedException, CountryNotFoundException {
        return ResponseEntity.ok(service.getCountryFlagURL(countryName));
    }
}

