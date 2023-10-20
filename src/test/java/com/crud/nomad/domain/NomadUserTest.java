package com.crud.nomad.domain;

import com.crud.nomad.domain.enums.TripStatus;
import com.crud.nomad.domain.enums.VacType;
import com.crud.nomad.respository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        user = new User(1L, "Jan", "Kowalski", "Poland", "Login", "Password", "USER");
    }

    @Test
    void getUserId() {
        //Then
        assertEquals(1L, user.getUserId());
    }

    @Test
    void getFirstName() {
        //Then
        assertEquals("Jan", user.getFirstName());
    }

    @Test
    void getSurname() {
        //Then
        assertEquals("Kowalski", user.getSurname());
    }

    @Test
    void getHomeland() {
        //Then
        assertEquals("Poland", user.getHomeland());
    }

    @Test
    void getTripList() {
        //Given
        Set<Trip> tripList = new HashSet<>();
        Trip trip = new Trip(1L, LocalDate.of(2023,12,12), LocalDate.of(2024,12,12), "Canada", TripStatus.PLANNED, new HashSet<>());
        tripList.add(trip);

        //When
        user.setTripList(tripList);

        //Then
        assertEquals(tripList, user.getTripList());
    }

    @Test
    void getVaccinations() {
        //Then
        assertNull(user.getVaccinationList());
    }

    @Test
    void setFirstName() {
        //When
        user.setFirstName("Adam");

        //Then
        assertEquals("Adam", user.getFirstName());
    }

    @Test
    void setSurname() {
        //When
        user.setSurname("Nowak");

        //Then
        assertEquals("Nowak", user.getSurname());
    }

    @Test
    void setHomeland() {
        //When
        user.setHomeland("Germany");

        //Then
        assertEquals("Germany", user.getHomeland());
    }

    @Test
    void setTripList() {
        //Given
        Set<Trip> tripList = new HashSet<>();
        Trip trip = new Trip(1L, LocalDate.of(2023, 10, 10), LocalDate.of(2023, 10, 15), "Paris", TripStatus.PLANNED);
        tripList.add(trip);

        //When
        user.setTripList(tripList);

        //Then
        assertEquals(tripList, user.getTripList());
    }

    @Test
    void setVaccinationList() {
        //Given
        List<Vaccination> vaccinationList = new ArrayList<>();
        Vaccination vaccination = new Vaccination(1L, "Covid-19", LocalDate.of(2023, 9, 30), VacType.COMPLETED);
        vaccinationList.add(vaccination);

        //When
        user.setVaccinationList(vaccinationList);

        //Then
        assertEquals(vaccinationList, user.getVaccinationList());
    }
}