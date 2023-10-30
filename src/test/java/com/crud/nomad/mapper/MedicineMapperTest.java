package com.crud.nomad.mapper;

import com.crud.nomad.domain.Medicine;
import com.crud.nomad.domain.dto.MedicineDto;
import com.crud.nomad.domain.enums.MedType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MedicineMapperTest {
    private MedicineMapper medicineMapper;
    @BeforeEach
    void setUp() {
        medicineMapper = new MedicineMapper();
    }

    @Test
    void testMapToMedicineDto() {
        //Given
        Medicine medicine = new Medicine(1L, "Apap", "Headache", MedType.BASIC, LocalDate.of(2025,12,12), new HashSet<>());

        //When
        MedicineDto medicineDto = medicineMapper.mapToMedicineDto(medicine);

        //Then
        assertEquals(medicine.getMedicineId(), medicineDto.getMedicineId());
        assertEquals(medicine.getMedicineName(), medicineDto.getMedicineName());
        assertEquals(medicine.getDesignation(), medicineDto.getDesignation());
        assertEquals(medicine.getMedType(),medicineDto.getMedType());
        assertEquals(medicine.getExpiryDate(), medicineDto.getExpiryDate());
    }

    @Test
    void testMapToMedicine() {
        //Given
        MedicineDto medicineDto = new MedicineDto(1L, "Apap", "Headache", MedType.BASIC, LocalDate.of(2025,12,12));

        //When
        Medicine medicine = medicineMapper.mapToMedicine(medicineDto);

        //Then
        assertEquals(medicineDto.getMedicineId(), medicine.getMedicineId());
        assertEquals(medicineDto.getMedicineName(), medicine.getMedicineName());
        assertEquals(medicineDto.getDesignation(), medicine.getDesignation());
        assertEquals(medicineDto.getMedType(),medicine.getMedType());
        assertEquals(medicineDto.getExpiryDate(), medicine.getExpiryDate());
    }

    @Test
    void testMapToMedicineDtoList() {
        //Given
        Medicine medicine1 = new Medicine(1L, "Apap", "Headache", MedType.BASIC, LocalDate.of(2025,12,12), new HashSet<>());
        Medicine medicine2 = new Medicine(2L, "Apap", "Headache", MedType.BASIC, LocalDate.of(2025,12,12), new HashSet<>());
        List<Medicine> medicineList = new ArrayList<>();
        medicineList.add(medicine1);
        medicineList.add(medicine2);

        //When
        List<MedicineDto> medicineDtoList = medicineMapper.mapToMedicineDtoList(medicineList);

        //Then
        assertEquals(medicineList.size(), medicineDtoList.size());

    }
}