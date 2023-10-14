package com.crud.nomad.scheduler;

import com.crud.nomad.domain.Trip;
import com.crud.nomad.domain.enums.TripStatus;
import com.crud.nomad.respository.TripRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class TripStatusActuatorScheduler {
    private TripRepository tripRepository;

    @Scheduled(cron = "0 0 * * * *")
    public void updateStatus() {
        List<Trip> allTrips = (List<Trip>) tripRepository.findAll();
        allTrips.forEach(e -> {
            if (e.getTripStatus() != null) {
                if (e.getDateStart().isAfter(LocalDate.now()) && e.getDateEnd().isAfter(LocalDate.now())) {
                    e.setTripStatus(TripStatus.PLANNED);
                } else if (e.getDateStart().isBefore(LocalDate.now()) && e.getDateEnd().isAfter(LocalDate.now())) {
                    e.setTripStatus(TripStatus.ACTIVE);
                } else if (e.getDateStart().isBefore(LocalDate.now()) && e.getDateEnd().isBefore(LocalDate.now())) {
                    e.setTripStatus(TripStatus.ENDED);
                    tripRepository.save(e);
                }
            }
        });
    }
}
