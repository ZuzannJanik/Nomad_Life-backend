package com.crud.nomad.controller;

import com.crud.nomad.domain.Country;
import com.crud.nomad.exceptions.CountryNotFoundException;
import com.crud.nomad.service.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(CountryControllerTest.class)
class CountryControllerTest {
    @Mock
    private CountryService countryService;
    @InjectMocks
    private CountryController countryController;
    private MockMvc mockMvc;
    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(countryController).build();
    }

    @Test
    void shouldReturnCountryWithFlagUrl() throws Exception, CountryNotFoundException {
        //Given
        String countryName = "Poland";
        String flagUrl = "https://rest-countries-gamma.vercel.app/emojis/svg/1f1f5-1f1f1.svg";
        Country country = new Country(1L, countryName, flagUrl);
        when(countryService.getCountryFlagURL(countryName)).thenReturn(country);

        //When
        ResultActions resultActions = mockMvc.perform(get("/api/v1/countries/{countryName}", countryName));

        //Then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.countryId").value(1L))
                .andExpect(jsonPath("$.countryName").value(countryName))
                .andExpect(jsonPath("$.flagUrl").value(flagUrl));
        verify(countryService).getCountryFlagURL(countryName);
        }
    }
