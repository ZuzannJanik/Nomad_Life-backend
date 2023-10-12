package com.crud.nomad.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.crud.nomad.domain.Country;
import com.crud.nomad.exceptions.CountryNotFoundException;
import com.crud.nomad.respository.CountryRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CountryServiceTest {

    @Mock
    private CountryRepository repository;

    @InjectMocks
    private CountryService service;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnCountryWhenFoundById() throws CountryNotFoundException {
        //Given
        Long countryId = 1L;
        Country expectedCountry = new Country(1L, "Poland", "https://example.com/poland.png");
        when(repository.findById(countryId)).thenReturn(Optional.of(expectedCountry));

        //When
        Country actualCountry = service.getCountry(countryId);

        //Then
        assertEquals(expectedCountry, actualCountry);
        verify(repository, times(1)).findById(countryId);
    }

    @Test
    public void shouldThrowExceptionWhenNotFoundById() {
        //Given
        Long countryId = 2L;
        when(repository.findById(countryId)).thenReturn(Optional.empty());

        //When and then
        assertThrows(CountryNotFoundException.class, () -> service.getCountry(countryId));
        verify(repository, times(1)).findById(countryId);
    }
}