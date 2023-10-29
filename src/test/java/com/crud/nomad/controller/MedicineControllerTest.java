package com.crud.nomad.controller;

import com.crud.nomad.domain.Medicine;
import com.crud.nomad.domain.dto.MedicineDto;
import com.crud.nomad.domain.enums.MedType;
import com.crud.nomad.mapper.MedicineMapper;
import com.crud.nomad.service.MedicineService;
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
import java.util.HashSet;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@SpringJUnitWebConfig
@WebMvcTest(MedicineController.class)
public class MedicineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicineService dbService;
    @SpyBean
    private MedicineMapper MedicineMapper;

    @Test
    void shouldFetchAllMedicines() throws Exception {
        //Given
        when(dbService.getAllMedicines()).thenReturn(List.of(new Medicine(1L, "Apap", "Headache", MedType.BASIC, LocalDate.of(2025,12,12), new HashSet<>())));

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/v1/medicines")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    void shouldFetchMedicineById() throws Exception {
        //Given
        Medicine medicine = new Medicine(1L, "Apap", "Headache", MedType.BASIC, LocalDate.of(2025,12,12), new HashSet<>());
        when(dbService.getMedicine(1L)).thenReturn(medicine);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/v1/medicines/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.medicineId", Matchers.is(1)));
    }

    @Test
    void shouldDeleteMedicine() throws Exception {
        //Given
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/v1/medicines/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldUpdateMedicine() throws Exception {
        //Given
        Medicine medicine = new Medicine(1L, "Apap", "Headache", MedType.BASIC, LocalDate.of(2025,12,12), new HashSet<>());
        MedicineDto medicineDto = new MedicineDto();
        when(dbService.saveMedicine(any(Medicine.class))).thenReturn(medicine);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();
        String jsonContent = gson.toJson(medicineDto);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/medicines")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldCreateTrip() throws Exception {
        //Given
        MedicineDto medicineDto = new MedicineDto();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();

        String jsonContent = gson.toJson(medicineDto);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/medicines")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}