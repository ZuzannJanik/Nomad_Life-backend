package com.crud.nomad.mapper;

import com.crud.nomad.domain.Country;
import com.crud.nomad.domain.dto.CountryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class CountryMapper {
    public Country mapToCountry(final CountryDto countryDto) {
        return new Country(
                countryDto.getCountryId(),
                countryDto.getCountryName(),
                countryDto.getFlagUrl()
        );
    }

    public CountryDto mapToCountryDto(final Country country) {
        return new CountryDto(
                country.getCountryId(),
                country.getCountryName(),
                country.getFlagUrl()
        );
    }

    public List<CountryDto> mapToCountryDtoList(final List<Country> countryList) {
        return countryList.stream()
                .map(this::mapToCountryDto)
                .collect(Collectors.toList());
    }
}
