package com.crud.nomad.service;

import com.crud.nomad.domain.Trip;
import com.crud.nomad.domain.enums.TripStatus;
import com.crud.nomad.exceptions.TripNotFoundException;
import com.crud.nomad.respository.TripRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TripServiceTest {

    @Mock
    private TripRepository tripRepository;

    @InjectMocks
    private TripService tripService;

    @Test
    public void testGetAllTrips() {
        //Given
        Trip trip1 = new Trip(1L, LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 15), "France", TripStatus.PLANNED, new HashSet<>());
        Trip trip2 = new Trip(2L, LocalDate.of(2023, 2, 1), LocalDate.of(2023, 2, 28), "Italy", TripStatus.ENDED, new HashSet<>());
        List<Trip> trips = Arrays.asList(trip1, trip2);
        when(tripRepository.findAll()).thenReturn(trips);

        //When
        List<Trip> result = tripService.getAllTrips();

        //Then
        assertEquals(trips, result);
        verify(tripRepository, times(1)).findAll();
    }

    @Test
    public void testSaveTrip() {
        //Given
        Trip trip = new Trip(3L, LocalDate.of(2023, 3, 1), LocalDate.of(2023, 3, 31), "Spain", TripStatus.ACTIVE, new HashSet<>());
        when(tripRepository.save(trip)).thenReturn(trip);

        //When
        Trip result = tripService.saveTrip(trip);

        //Then
        assertEquals(trip, result);
        verify(tripRepository, times(1)).save(trip);
    }

    @Test
    public void testGetTrip() throws TripNotFoundException {
        //Given
        Trip trip = new Trip(4L, LocalDate.of(2023, 4, 1), LocalDate.of(2023, 4, 30), "Germany", TripStatus.ENDED, new HashSet<>());
        when(tripRepository.findById(4L)).thenReturn(Optional.of(trip));

        //When
        Trip result = tripService.getTrip(4L);

        //Then
        assertEquals(trip, result);
        verify(tripRepository, times(1)).findById(4L);
    }

    @Test
    public void testGetTripThrowsException() {
        //Given
        when(tripRepository.findById(5L)).thenReturn(Optional.empty());

        //When and then
        assertThrows(TripNotFoundException.class, () -> tripService.getTrip(5L));
        verify(tripRepository, times(1)).findById(5L);
    }

    @Test
    public void testDeleteTrip() {
        //Given
        doNothing().when(tripRepository).deleteById(6L);

        //When
        tripService.deleteTrip(6L);

        //Then
        verify(tripRepository, times(1)).deleteById(6L);
    }
}