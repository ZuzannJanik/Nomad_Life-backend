package com.crud.nomad.respository;

import com.crud.nomad.domain.Trip;
import com.crud.nomad.domain.Vaccination;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VaccinationRepository extends CrudRepository<Vaccination, Long> {
    @Override
    List<Vaccination> findAll();

    @Override
    Vaccination save(Vaccination vaccination);

    @Override
    Optional<Vaccination> findById(Long vacId);

    @Override
    void deleteById(Long vacId);
}
