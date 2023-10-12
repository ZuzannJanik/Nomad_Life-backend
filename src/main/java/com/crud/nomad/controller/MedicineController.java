package com.crud.nomad.controller;

import com.crud.nomad.domain.Medicine;
import com.crud.nomad.domain.dto.MedicineDto;
import com.crud.nomad.exceptions.MedicineNotFoundException;
import com.crud.nomad.mapper.MedicineMapper;
import com.crud.nomad.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medicines")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MedicineController {
    private final MedicineMapper medicineMapper;
    private final MedicineService service;

    @GetMapping
    public ResponseEntity<List<MedicineDto>> getMedicines() {
        List<Medicine> medicines = service.getAllMedicines();
        return ResponseEntity.ok(medicineMapper.mapToMedicineDtoList(medicines));
    }

    @GetMapping(value = "{medicineId}")
    public ResponseEntity<MedicineDto> getMedicine(@PathVariable Long medicineId) throws MedicineNotFoundException {
        return ResponseEntity.ok(medicineMapper.mapToMedicineDto(service.getMedicine(medicineId)));
    }

    @DeleteMapping(value = "{medicineId}")
    public ResponseEntity<MedicineDto> deleteMedicine(@PathVariable Long medicineId)  throws MedicineNotFoundException {
        service.deleteMedicine(medicineId);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<MedicineDto> updateMedicine(@RequestBody MedicineDto medicineDto) {
        Medicine medicine = medicineMapper.mapToMedicine(medicineDto);
        Medicine savedMedicine = service.saveMedicine(medicine);
        return ResponseEntity.ok(medicineMapper.mapToMedicineDto(savedMedicine));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createMedicine(@RequestBody MedicineDto medicineDto) {
        Medicine medicine = medicineMapper.mapToMedicine(medicineDto);
        service.saveMedicine(medicine);
        return ResponseEntity.ok().build();
    }
}
