package com.crud.nomad.service;

import com.crud.nomad.domain.Trip;
import com.crud.nomad.exceptions.TripNotFoundException;
import com.crud.nomad.respository.TripRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TripService {
    private final TripRepository repository;
    public List<Trip> getAllTrips() {

        return repository.findAll();
    }
    public Trip saveTrip(final Trip trip) {

        return repository.save(trip);
    }
    public Trip getTrip(final Long tripId) throws TripNotFoundException {
        return repository.findById(tripId).orElseThrow(TripNotFoundException::new);
    }
    public void deleteTrip(final Long tripId) {

        repository.deleteById(tripId);
    }
}
