package com.crud.nomad.service;

import com.crud.nomad.domain.Country;
import com.crud.nomad.exceptions.CountryNotFoundException;
import com.crud.nomad.respository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CountryService {
    private final CountryRepository repository;
    public List<Country> getAllCountries() {
        return repository.findAll();
    }
    public Country getCountry(final Long countryId) throws CountryNotFoundException {
        return repository.findById(countryId).orElseThrow(CountryNotFoundException::new);
    }
}
