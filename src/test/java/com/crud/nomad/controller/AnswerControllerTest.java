package com.crud.nomad.controller;

import com.crud.nomad.domain.Answer;
import com.crud.nomad.service.AnswerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AnswerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AnswerService answerService;
    @Test
    public void testGenerateSnippet() throws Exception {
        // Given
        when(answerService.generateSnippet("paracetamol")).thenReturn(Answer.builder().question("paracetamol").snippet("Paracetamol (acetaminophen or para-hydroxyacetanilide) is a non-opioid analgesic and antipyretic agent used to treat fever and mild to moderate pain. ... It is a ...").link("https://en.m.wikipedia.org/wiki/Paracetamol").build());
        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/answers/paracetamol")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.question").value("paracetamol"))
                .andExpect(jsonPath("$.snippet").value("Paracetamol (acetaminophen or para-hydroxyacetanilide) is a non-opioid analgesic and antipyretic agent used to treat fever and mild to moderate pain. ... It is a ..."))
                .andExpect(jsonPath("$.link").value("https://en.m.wikipedia.org/wiki/Paracetamol"));
    }
}