package com.crud.nomad.controller;


import com.crud.nomad.domain.NomadUser;
import com.crud.nomad.domain.dto.NomadUserDto;
import com.crud.nomad.mapper.NomadUserMapper;
import com.crud.nomad.service.NomadUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@SpringJUnitWebConfig
@WebMvcTest(NomadUserController.class)
class NomadNomadUserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private NomadUserService dbService;
    @SpyBean
    private NomadUserMapper nomadUserMapper;

    @Test
    void shouldFetchAllUsers() throws Exception {
        //Given
        when(dbService.getAllNomadUsers()).thenReturn(List.of(new NomadUser(1L, "1Name", "2Name", "Poland", "Login", "Haslo", "USER", new HashSet<>(), new ArrayList<>())));

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    void shouldFetchUserById() throws Exception {
        //Given
        NomadUser nomadUser = new NomadUser(1L, "1Name", "2Name", "Poland", "Login","Haslo", "USER", new HashSet<>(), new ArrayList<>());
        when(dbService.getNomadUser(1L)).thenReturn(nomadUser);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", Matchers.is(1)));
    }
    @Test
    void shouldDeleteUser() throws Exception {
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldUpdateUser() throws Exception {
        //Given
        NomadUser nomadUser = new NomadUser(1L, "1Name", "2Name", "Poland", "Login","Haslo", "USER", new HashSet<>(), new ArrayList<>());
        NomadUserDto userDto = new NomadUserDto();
        when(dbService.saveNomadUser(any(NomadUser.class))).thenReturn(nomadUser);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();
        String jsonContent = gson.toJson(userDto);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldCreateUser() throws Exception {
        //Given
        NomadUser nomadUser = new NomadUser(1L, "1Name", "2Name", "Poland", "Login","Haslo","USER", new HashSet<>(), new ArrayList<>());
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();
        String jsonContent = gson.toJson(nomadUser);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}