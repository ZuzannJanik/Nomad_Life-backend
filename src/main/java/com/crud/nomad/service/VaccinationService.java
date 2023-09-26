package com.crud.nomad.service;

import com.crud.nomad.domain.Vaccination;
import com.crud.nomad.exceptions.VaccinationNotFoundException;
import com.crud.nomad.respository.VaccinationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VaccinationService {
    private final VaccinationRepository repository;
    public List<Vaccination> getAllVaccinations() {

        return repository.findAll();
    }
    public Vaccination saveVaccination(final Vaccination vaccination) {

        return repository.save(vaccination);
    }
    public Vaccination getVaccination(final Long vacId) throws VaccinationNotFoundException {
        return repository.findById(vacId).orElseThrow(VaccinationNotFoundException::new);
    }
    public void deleteVaccination(final Long vacId) {

        repository.deleteById(vacId);
    }
}
