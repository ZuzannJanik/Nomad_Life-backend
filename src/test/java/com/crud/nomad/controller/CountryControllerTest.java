package com.crud.nomad.controller;

import com.crud.nomad.domain.Country;
import com.crud.nomad.mapper.CountryMapper;
import com.crud.nomad.service.CountryService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(CountryControllerTest.class)
class CountryControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CountryService service;
    @SpyBean
    private CountryMapper countryMapper;

    @Test
    void shouldFetchAllCountries() throws Exception {
        //Given
        when(service.getAllCountries()).thenReturn(List.of(new Country(1L,"Poland", "addressURL")));

        //When and Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/v1/countries")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    void getCountry() {
    }
}