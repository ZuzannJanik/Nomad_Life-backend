package com.crud.nomad.controller;

import com.crud.nomad.domain.User;
import com.crud.nomad.domain.Vaccination;
import com.crud.nomad.domain.dto.UserDto;
import com.crud.nomad.mapper.UserMapper;
import com.crud.nomad.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import com.google.gson.Gson;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@SpringJUnitWebConfig
@WebMvcTest(UserController.class)
class UserControllerTest {

        @Autowired
        private MockMvc mockMvc;
        @MockBean
        private UserService dbService;
        @SpyBean
        private UserMapper userMapper;

        @Test
        void shouldFetchAllUsers() throws Exception {
            //Given
            when(dbService.getAllUsers()).thenReturn(List.of(new User(1L, "1Name", "2Name", "Poland", new HashSet<>(), new Vaccination())));

            //When&Then
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .get("/v1/users")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().is(200))
                    .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
        }

        @Test
        void shouldFetchUserById() throws Exception {
            //Given
            User user = new User(1L, "1Name", "2Name", "Poland", new HashSet<>(), new Vaccination());
            when(dbService.getUser(1L)).thenReturn(user);

            //When&Then
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .get("/v1/users/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().is(200))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.userId", Matchers.is(1)));
        }

        @Test
        void shouldDeleteUser() throws Exception {
            //Given
            //When & Then
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .delete("/v1/users/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().is(200));
        }

        @Test
        void shouldUpdateUser() throws Exception {
            //Given
            User user = new User(1L, "1Name", "2Name", "Poland", new HashSet<>(), new Vaccination());
            UserDto userDto = new UserDto(1L, "1Name", "2Name", "Poland", new HashSet<>(), new Vaccination());
            when(dbService.saveUser(any(User.class))).thenReturn(user);

            Gson gson = new Gson();
            String jsonContent = gson.toJson(userDto);

            //When&Then
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .post("/v1/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .characterEncoding("UTF-8")
                            .content(jsonContent))
                    .andExpect(MockMvcResultMatchers.status().is(200));
        }

        @Test
        void shouldCreateUser() throws Exception {
            //Given
            UserDto userDto = new UserDto(1L, "1Name", "2Name", "Poland", new HashSet<>(), new Vaccination());

            Gson gson = new Gson();
            String jsonContent = gson.toJson(userDto);

            //When&Then
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .post("/v1/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .characterEncoding("UTF-8")
                            .content(jsonContent))
                    .andExpect(MockMvcResultMatchers.status().is(200));
        }
}