package com.crud.nomad.controller;

import com.crud.nomad.exceptions.CountryNotFoundException;
import com.crud.nomad.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/countries")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CountryController {
    private final CountryService service;

    @GetMapping(value = "{countryName}")
    ResponseEntity<?> getCountryFlagURL(@PathVariable String countryName) throws CountryNotFoundException {
        return ResponseEntity.ok(service.getCountryFlagURL(countryName));
    }
}

