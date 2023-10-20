package com.crud.nomad.domain;

import com.crud.nomad.domain.enums.TripStatus;
import com.crud.nomad.domain.enums.VacType;
import com.crud.nomad.respository.NomadUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class NomadUserTest {

    private NomadUser nomadUser;
    private NomadUserRepository nomadUserRepository;

    @BeforeEach
    void setUp() {
        nomadUser = new NomadUser(1L, "Jan", "Kowalski", "Poland", "Login", "Password", "USER");
    }

    @Test
    void getUserId() {
        //Then
        assertEquals(1L, nomadUser.getUserId());
    }

    @Test
    void getFirstName() {
        //Then
        assertEquals("Jan", nomadUser.getFirstName());
    }

    @Test
    void getSurname() {
        //Then
        assertEquals("Kowalski", nomadUser.getSurname());
    }

    @Test
    void getHomeland() {
        //Then
        assertEquals("Poland", nomadUser.getHomeland());
    }

    @Test
    void getTripList() {
        //Given
        Set<Trip> tripList = new HashSet<>();
        Trip trip = new Trip(1L, LocalDate.of(2023,12,12), LocalDate.of(2024,12,12), "Canada", TripStatus.PLANNED, new HashSet<>());
        tripList.add(trip);

        //When
        nomadUser.setTripList(tripList);

        //Then
        assertEquals(tripList, nomadUser.getTripList());
    }

    @Test
    void getVaccinations() {
        //Then
        assertNull(nomadUser.getVaccinationList());
    }

    @Test
    void setFirstName() {
        //When
        nomadUser.setFirstName("Adam");

        //Then
        assertEquals("Adam", nomadUser.getFirstName());
    }

    @Test
    void setSurname() {
        //When
        nomadUser.setSurname("Nowak");

        //Then
        assertEquals("Nowak", nomadUser.getSurname());
    }

    @Test
    void setHomeland() {
        //When
        nomadUser.setHomeland("Germany");

        //Then
        assertEquals("Germany", nomadUser.getHomeland());
    }

    @Test
    void setTripList() {
        //Given
        Set<Trip> tripList = new HashSet<>();
        Trip trip = new Trip(1L, LocalDate.of(2023, 10, 10), LocalDate.of(2023, 10, 15), "Paris", TripStatus.PLANNED);
        tripList.add(trip);

        //When
        nomadUser.setTripList(tripList);

        //Then
        assertEquals(tripList, nomadUser.getTripList());
    }

    @Test
    void setVaccinationList() {
        //Given
        List<Vaccination> vaccinationList = new ArrayList<>();
        Vaccination vaccination = new Vaccination(1L, "Covid-19", LocalDate.of(2023, 9, 30), VacType.COMPLETED);
        vaccinationList.add(vaccination);

        //When
        nomadUser.setVaccinationList(vaccinationList);

        //Then
        assertEquals(vaccinationList, nomadUser.getVaccinationList());
    }
}