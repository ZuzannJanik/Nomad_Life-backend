package com.crud.nomad.mapper;

import com.crud.nomad.domain.Trip;
import com.crud.nomad.domain.dto.TripDto;
import com.crud.nomad.domain.enums.TripStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TripMapperTest {
    private TripMapper tripMapper;

    @BeforeEach
    void setUp() {
        tripMapper = new TripMapper();
    }

    @Test
    void mapToTripDto() {
        //Given
    Trip trip = new Trip(3L, LocalDate.of(2023,12,12), LocalDate.of(2024,12,12), "Canada", TripStatus.PLANNED, new HashSet<>());

        //When
    TripDto tripDto = tripMapper.mapToTripDto(trip);

        //Then
        assertEquals(trip.getTripId(), tripDto.getTripId());
        assertEquals(trip.getDateStart(), tripDto.getDateStart());
        assertEquals(trip.getDateEnd(), tripDto.getDateEnd());
        assertEquals(trip.getDestinationCountry(), tripDto.getDestinationCountry());
        assertEquals(trip.getTripStatus(), tripDto.getTripStatus());
    }

    @Test
    void mapToTrip() {
        //Given
        TripDto tripDto = new TripDto(1L, LocalDate.of(2023,12,12), LocalDate.of(2024,12,12), "Canada", TripStatus.PLANNED, new HashSet<>());

        //When
        Trip trip = tripMapper.mapToTrip(tripDto);

        //Then
        assertEquals(tripDto.getTripId(), trip.getTripId());
        assertEquals(tripDto.getDateStart(), trip.getDateStart());
        assertEquals(tripDto.getDateEnd(), trip.getDateEnd());
        assertEquals(tripDto.getDestinationCountry(), trip.getDestinationCountry());
        assertEquals(tripDto.getTripStatus(), trip.getTripStatus());
    }

    @Test
    void mapToTripDtoList() {
        //Given
        Trip trip1 = new Trip(3L, LocalDate.of(2023,12,12), LocalDate.of(2024,12,12), "Canada", TripStatus.PLANNED, new HashSet<>());
        Trip trip2 = new Trip(4L, LocalDate.of(2023,12,12), LocalDate.of(2024,12,12), "Canada", TripStatus.PLANNED, new HashSet<>());
        List<Trip> tripList = new ArrayList<>();
        tripList.add(trip1);
        tripList.add(trip2);

        //When
        List<TripDto> tripDtoList = tripMapper.mapToTripDtoList(tripList);

        //Then
        assertEquals(tripList.size(), tripDtoList.size());
    }
}