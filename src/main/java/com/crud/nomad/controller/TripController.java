package com.crud.nomad.controller;

import com.crud.nomad.domain.Trip;
import com.crud.nomad.domain.dto.TripDto;
import com.crud.nomad.exceptions.TripNotFoundException;
import com.crud.nomad.mapper.TripMapper;
import com.crud.nomad.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/trips")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TripController {
    private final TripMapper tripMapper;
    private final TripService tripService;

    @GetMapping
    public ResponseEntity<List<TripDto>> getTrips() {
        List<Trip> trips = tripService.getAllTrips();
        return ResponseEntity.ok(tripMapper.mapToTripDtoList(trips));
    }

    @GetMapping(value = "{tripId}")
    public ResponseEntity<TripDto> getTrip(@PathVariable Long tripId) throws TripNotFoundException {
        return ResponseEntity.ok(tripMapper.mapToTripDto(tripService.getTrip(tripId)));
    }

    @DeleteMapping(value = "{tripId}")
    public ResponseEntity<TripDto> deleteTrip(@PathVariable Long tripId) {
        tripService.deleteTrip(tripId);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<TripDto> updateTrip(@RequestBody TripDto tripDto) {
        Trip trip = tripMapper.mapToTrip(tripDto);
        Trip savedTrip = tripService.saveTrip(trip);
        return ResponseEntity.ok(tripMapper.mapToTripDto(savedTrip));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createTrip(@RequestBody TripDto tripDto) {
        Trip trip = tripMapper.mapToTrip(tripDto);
        tripService.saveTrip(trip);
        return ResponseEntity.ok().build();
    }
}
