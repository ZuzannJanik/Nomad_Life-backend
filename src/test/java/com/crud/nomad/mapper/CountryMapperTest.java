package com.crud.nomad.mapper;

import com.crud.nomad.domain.Country;
import com.crud.nomad.domain.dto.CountryDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountryMapperTest {

    private CountryMapper countryMapper = new CountryMapper();

    @Test
    void testMapToCountry() {
        //Given
        CountryDto countryDto = new CountryDto(1L, "Poland", "https://flagpedia.net/data/flags/w580/pl.png");

        //When
        Country country = countryMapper.mapToCountry(countryDto);

        //Then
        assertEquals(countryDto.getCountryId(), country.getCountryId());
        assertEquals(countryDto.getName(), country.getName());
        assertEquals(countryDto.getFlagUrl(), country.getFlagUrl());
    }

    @Test
    void testMapToCountryDto() {
        //Given
        Country country = new Country(2L, "China", "https://flagpedia.net/data/flags/w580/cn.png");

        //When
        CountryDto countryDto = countryMapper.mapToCountryDto(country);

        //Then
        assertEquals(country.getCountryId(), countryDto.getCountryId());
        assertEquals(country.getName(), countryDto.getName());
        assertEquals(country.getFlagUrl(), countryDto.getFlagUrl());
    }

    @Test
    void testMapToCountryDtoList() {
        //Given
        List<Country> countryList = new ArrayList<>();
        countryList.add(new Country(3L, "France", "https://flagpedia.net/data/flags/w580/fr.png"));
        countryList.add(new Country(4L, "Germany", "https://flagpedia.net/data/flags/w580/de.png"));

        List<CountryDto> expectedCountryDtoList = new ArrayList<>();
        expectedCountryDtoList.add(new CountryDto(3L, "France", "https://flagpedia.net/data/flags/w580/fr.png"));
        expectedCountryDtoList.add(new CountryDto(4L, "Germany", "https://flagpedia.net/data/flags/w580/de.png"));

        //When
        List<CountryDto> actualCountryDtoList = countryMapper.mapToCountryDtoList(countryList);

        //Then
        assertEquals(expectedCountryDtoList, actualCountryDtoList);
    }
}