package com.crud.nomad.domain;

import com.crud.nomad.domain.enums.UserRole;
import com.crud.nomad.domain.enums.VacType;
import com.crud.nomad.respository.VaccinationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class VaccinationTest {
    private Vaccination vaccination;
    private VaccinationRepository vaccinationRepository;

    @BeforeEach
    void setUp() {
        vaccination = new Vaccination(1L, "Covid-19", LocalDate.of(2023, 9, 30), VacType.COMPLETED);
    }

    @Test
    void getVacId() {
        //Then
        assertEquals(1L, vaccination.getVacId());
    }

    @Test
    void getDiseaseName() {
        //Then
        assertEquals("Covid-19", vaccination.getDiseaseName());
    }

    @Test
    void getLastVac() {
        //Then
        assertEquals(LocalDate.of(2023, 9, 30), vaccination.getLastVac());
    }

    @Test
    void getVacType() {
        //Then
        assertEquals(VacType.COMPLETED, vaccination.getVacType());
    }

    @Test
    void getUser() {
        //Then
        assertNull(vaccination.getNomadUser());
    }

    @Test
    void setDiseaseName() {
        //When
        vaccination.setDiseaseName("Hepatitis B");

        //Then
        assertEquals("Hepatitis B", vaccination.getDiseaseName());
    }

    @Test
    void setLastVac() {
        //When
        vaccination.setLastVac(LocalDate.of(2023, 10, 1));

        //Then
        assertEquals(LocalDate.of(2023, 10, 1), vaccination.getLastVac());
    }

    @Test
    void setVacType() {
        //When
        vaccination.setVacType(VacType.COMPLETED);

        //Then
        assertEquals(VacType.COMPLETED, vaccination.getVacType());
    }

    @Test
    void setUser() {
        //Given
        NomadUser nomadUser = new NomadUser(1L, "Jan", "Kowalski", "Poland", "Login", "Password", UserRole.USER);

        //When
        vaccination.setNomadUser(nomadUser);

        //Then
        assertEquals(nomadUser, vaccination.getNomadUser());
    }
}