package com.crud.nomad.scheduler;

import com.crud.nomad.domain.Trip;
import com.crud.nomad.domain.enums.TripStatus;
import com.crud.nomad.respository.TripRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.*;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@PrepareForTest({ LocalDate.class })
@RunWith(PowerMockRunner.class)
@SpringBootTest
class TripStatusActuatorSchedulerTest {
    LocalDate date = LocalDate.of(2015, 11, 12);
    @Mock
    private TripRepository tripRepository;
    @InjectMocks
    private TripStatusActuatorScheduler tripStatusActuatorScheduler;

    @Test
    void testShouldUpdateTripStatus() {
        //Given
        Trip trip1 = new Trip(1L, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 12, 12), "Poland", TripStatus.ENDED);
        Trip trip2 = new Trip(2L, LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 12), "Poland", TripStatus.ACTIVE);
        Trip trip3 = new Trip(3L, LocalDate.of(2025, 1, 1), LocalDate.of(2025, 12, 12), "Poland", TripStatus.PLANNED);
        List<Trip> allTrips = Arrays.asList(trip1, trip2, trip3);

        mockStatic(LocalDate.class);
        when(LocalDate.now()).thenReturn(date);
        when(tripRepository.findAll()).thenReturn(allTrips);

        //When
        tripStatusActuatorScheduler.updateStatus();

        //Then
        assertThat(trip1.getTripStatus()).isEqualTo(TripStatus.PLANNED);
        assertThat(trip2.getTripStatus()).isEqualTo(TripStatus.PLANNED);
        assertThat(trip3.getTripStatus()).isEqualTo(TripStatus.PLANNED);
    }
}
