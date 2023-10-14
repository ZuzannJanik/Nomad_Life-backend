package com.crud.nomad.controller;

import com.crud.nomad.domain.User;
import com.crud.nomad.domain.Vaccination;
import com.crud.nomad.domain.dto.UserDto;
import com.crud.nomad.domain.dto.VaccinationDto;
import com.crud.nomad.domain.enums.VacType;
import com.crud.nomad.mapper.VaccinationMapper;
import com.crud.nomad.service.VaccinationService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(VaccinationController.class)
public class VaccinationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VaccinationService dbService;
    @SpyBean
    private VaccinationMapper vaccinationMapper;

    @Test
    void shouldFetchAllVaccinations() throws Exception {
        //Given
        when(dbService.getAllVaccinations()).thenReturn(List.of(new Vaccination(1L, "Disease",  LocalDate.of(2000, 12, 12), VacType.COMPLETED, new User())));

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/v1/vaccinations")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    void shouldFetchVaccinationById() throws Exception {
        //Given
        Vaccination vaccination = new Vaccination(1L, "Disease",  LocalDate.of(2000, 12, 12), VacType.COMPLETED, new User());
        when(dbService.getVaccination(1L)).thenReturn(vaccination);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/v1/vaccinations/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vacId", Matchers.is(1)));
    }

    @Test
    void shouldDeleteVaccination() throws Exception {
        //Given
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/v1/vaccinations/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldUpdateVaccination() throws Exception {
        //Given
        Vaccination vaccination = new Vaccination();
        VaccinationDto vaccinationDto = new VaccinationDto(1L, "Disease",  LocalDate.of(2000, 12, 12), VacType.COMPLETED);
        when(dbService.saveVaccination(any(Vaccination.class))).thenReturn(vaccination);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();
        String jsonContent = gson.toJson(vaccinationDto);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/vaccinations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldCreateVaccination() throws Exception {
        //Given
        VaccinationDto vaccinationDto = new VaccinationDto(1L, "Disease",  LocalDate.of(2000, 12, 12), VacType.COMPLETED);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();

        String jsonContent = gson.toJson(vaccinationDto);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/vaccinations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}

