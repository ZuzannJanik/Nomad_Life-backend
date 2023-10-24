package com.crud.nomad.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CountryTest {
    private Country country;
    @BeforeEach
    void setUp() {
        country = new Country(1L, "Poland", "https://example.com/poland.png");
    }

    @Test
    void getCountryId() {
        //Then
        assertEquals(1L, country.getCountryId());
    }

    @Test
    void getName() {
        //Then
        assertEquals("Poland", country.getCountryName());
    }

    @Test
    void getFlagUrl() {
        //Then
        assertEquals("https://example.com/poland.png", country.getFlagUrl());
    }

    @Test
    void setName() {
        //When
        country.setCountryName("Germany");

        //Then
        assertEquals("Germany", country.getCountryName());
    }

    @Test
    void setFlagUrl() {
        //When
        country.setFlagUrl("https://example.com/germany.png");

        //Then
        assertEquals("https://example.com/germany.png", country.getFlagUrl());
    }
}