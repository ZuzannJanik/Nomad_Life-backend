package com.crud.nomad.respository;

import com.crud.nomad.domain.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends CrudRepository<Country, Long> {
    @Override
    List<Country> findAll();
    @Override
    Optional<Country> findById(Long countryId);
    @Override
    Country save(Country country);
    @Override
    void deleteById(Long countryId);
}
