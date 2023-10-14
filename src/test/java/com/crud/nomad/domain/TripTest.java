package com.crud.nomad.domain;

import com.crud.nomad.domain.enums.TripStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TripTest {

    private Trip trip;

    @BeforeEach
    void setUp() {
        trip = new Trip(1L, LocalDate.of(2023, 10, 10), LocalDate.of(2023, 10, 15), "France", TripStatus.PLANNED);
    }

    @Test
    void getTripId() {
        //Then
        assertEquals(1L, trip.getTripId());
    }

    @Test
    void getDateStart() {
        //Then
        assertEquals(LocalDate.of(2023, 10, 10), trip.getDateStart());
    }

    @Test
    void getDateEnd() {
        //Then
        assertEquals(LocalDate.of(2023, 10, 15), trip.getDateEnd());
    }

    @Test
    void getDestinationCountry() {
        //Then
        assertEquals("France", trip.getDestinationCountry());
    }

    @Test
    void getTripStatus() {
        //Then
        assertEquals(TripStatus.PLANNED, trip.getTripStatus());
    }

    @Test
    void getUserList() {
        //Given
        Set<User> userList = new HashSet<>();
        User user = new User(1L, "Jan", "Kowalski", "Poland");
        userList.add(user);

        //When
       trip.setUserList(userList);

        //Then
        assertEquals(userList, trip.getUserList());
    }
}