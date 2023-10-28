package com.crud.nomad.controller;

import com.crud.nomad.domain.Vaccination;
import com.crud.nomad.domain.dto.VaccinationDto;
import com.crud.nomad.exceptions.VaccinationNotFoundException;
import com.crud.nomad.mapper.VaccinationMapper;
import com.crud.nomad.service.VaccinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vaccinations")
@RequiredArgsConstructor
@CrossOrigin("*")
public class VaccinationController {
    private final VaccinationService vaccinationService;
    private final VaccinationMapper vaccinationMapper;

    @GetMapping
    public ResponseEntity<List<VaccinationDto>> getVaccinations() {
        List<Vaccination> vaccinations = vaccinationService.getAllVaccinations();
        return ResponseEntity.ok(vaccinationMapper.mapToVaccinationDtoList(vaccinations));
    }

    @GetMapping(value = "{vacId}")
    public ResponseEntity<VaccinationDto> getVaccination(@PathVariable Long vacId) throws VaccinationNotFoundException {
        return ResponseEntity.ok(vaccinationMapper.mapToVaccinationDto(vaccinationService.getVaccination(vacId)));
    }

    @DeleteMapping(value = "{vacId}")
    public ResponseEntity<VaccinationDto> deleteVaccination(@PathVariable Long vacId) {
        vaccinationService.deleteVaccination(vacId);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<VaccinationDto> updateVaccination(@RequestBody VaccinationDto vaccinationDto) {
        Vaccination vaccination = vaccinationMapper.mapToVaccination(vaccinationDto);
        Vaccination savedVaccination = vaccinationService.saveVaccination(vaccination);
        return ResponseEntity.ok(vaccinationMapper.mapToVaccinationDto(savedVaccination));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createVaccination(@RequestBody VaccinationDto vaccinationDto) {
        Vaccination vaccination = vaccinationMapper.mapToVaccination(vaccinationDto);
        vaccinationService.saveVaccination(vaccination);
        return ResponseEntity.ok().build();
    }
}
