package com.crud.nomad.respository;

import com.crud.nomad.domain.Trip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface TripRepository extends CrudRepository<Trip, Long> {
    @Override
    List<Trip> findAll();

    @Override
    Trip save(Trip trip);

    @Override
    Optional<Trip> findById(Long tripId);

    @Override
    void deleteById(Long tripId);
}
