package com.crud.nomad.respository;

import com.crud.nomad.domain.Medicine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicineRepository extends CrudRepository<Medicine, Long> {
    @Override
    List<Medicine> findAll();

    @Override
    Medicine save(Medicine medicine);

    @Override
    Optional<Medicine> findById(Long medicineId);

    @Override
    void deleteById(Long medicineId);
}