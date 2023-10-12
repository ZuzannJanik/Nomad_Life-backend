package com.crud.nomad.service;

import com.crud.nomad.domain.Medicine;
import com.crud.nomad.exceptions.MedicineNotFoundException;
import com.crud.nomad.respository.MedicineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MedicineService {

    private final MedicineRepository repository;
    public List<Medicine> getAllMedicines() {
        return repository.findAll();
    }
    public Medicine saveMedicine(final Medicine medicine) {
        return repository.save(medicine);
    }
    public Medicine getMedicine(final Long medicineId) throws MedicineNotFoundException {
        return repository.findById(medicineId).orElseThrow(MedicineNotFoundException::new);
    }
    public void deleteMedicine(final Long medicineId) {
        repository.deleteById(medicineId);
    }
}
