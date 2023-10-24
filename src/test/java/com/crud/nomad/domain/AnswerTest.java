package com.crud.nomad.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnswerTest {
    private Answer answer;
    @BeforeEach
    void setUp() {
        answer = new Answer(1L, "Test question", "Test snippet", "Test link");
    }

    @Test
    void getAnswerId() {
        //Then
        assertEquals(1L, answer.getAnswerId());
    }

    @Test
    void getQuestion() {
        //Then
        assertEquals("Test question", answer.getQuestion());
    }

    @Test
    void getSnippet() {
        //Then
        assertEquals("Test snippet", answer.getSnippet());
    }
    @Test
    void getLink() {
        //Then
        assertEquals("Test link", answer.getLink());
    }

    @Test
    void setQuestion() {
        //When
        answer.setQuestion("Test");

        //Then
        assertEquals("Test", answer.getQuestion());
    }

    @Test
    void setSnippet() {
        //When
        answer.setSnippet("Test");

        //Then
        assertEquals("Test", answer.getSnippet());
    }
}