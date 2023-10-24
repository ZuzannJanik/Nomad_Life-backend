package com.crud.nomad.controller;

import com.crud.nomad.domain.Trip;
import com.crud.nomad.domain.enums.TripStatus;
import com.crud.nomad.domain.dto.TripDto;
import com.crud.nomad.mapper.TripMapper;
import com.crud.nomad.service.TripService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@SpringJUnitWebConfig
@WebMvcTest(TripController.class)
@AutoConfigureMockMvc(addFilters=false)
public class TripControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TripService dbService;
    @SpyBean
    private TripMapper tripMapper;

    @Test
    void shouldFetchAllTrips() throws Exception {
        //Given
        when(dbService.getAllTrips()).thenReturn(List.of(new Trip(1L, LocalDate.of(2023,12,12), LocalDate.of(2024,12,12), "Canada", TripStatus.PLANNED, new HashSet<>())));

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/v1/trips")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    void shouldFetchTripById() throws Exception {
        //Given
        Trip trip = new Trip(1L, LocalDate.of(2023,12,12), LocalDate.of(2024,12,12), "Canada", TripStatus.PLANNED, new HashSet<>());
        when(dbService.getTrip(1L)).thenReturn(trip);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/v1/trips/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.tripId", Matchers.is(1)));
    }

    @Test
    void shouldDeleteTrip() throws Exception {
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/v1/trips/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldUpdateTrip() throws Exception {
        //Given
        Trip trip = new Trip(1L, LocalDate.of(2023,12,12), LocalDate.of(2024,12,12), "Canada", TripStatus.PLANNED, new HashSet<>());
        TripDto tripDto = new TripDto();
        when(dbService.saveTrip(any(Trip.class))).thenReturn(trip);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();
        String jsonContent = gson.toJson(tripDto);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/trips")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldCreateTrip() throws Exception {
        //Given
        TripDto tripDto = new TripDto();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();

        String jsonContent = gson.toJson(tripDto);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/trips")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}