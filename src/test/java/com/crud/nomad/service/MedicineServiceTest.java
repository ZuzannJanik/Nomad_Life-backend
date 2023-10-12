package com.crud.nomad.service;

import com.crud.nomad.domain.Medicine;
import com.crud.nomad.domain.enums.MedType;
import com.crud.nomad.exceptions.MedicineNotFoundException;
import com.crud.nomad.respository.MedicineRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MedicineServiceTest {

    @Mock
    private MedicineRepository medicineRepository;

    @InjectMocks
    private MedicineService medicineService;

    @Test
    public void testGetAllMedicines() {
        //Given
        Medicine medicine1 = new Medicine(1L, "Apap", "Headache", MedType.BASIC, LocalDate.of(2025, 12, 12));
        Medicine medicine2 = new Medicine(2L, "Ibuprofen", "Anti-inflammatory", MedType.BASIC, LocalDate.of(2025, 11, 11));
        List<Medicine> medicines = Arrays.asList(medicine1, medicine2);
        when(medicineRepository.findAll()).thenReturn(medicines);

        //When
        List<Medicine> result = medicineService.getAllMedicines();

        //Then
        assertEquals(medicines, result);
        verify(medicineRepository, times(1)).findAll();
    }

    @Test
    public void testSaveMedicine() {
        //Given
        Medicine medicine = new Medicine(3L, "Paracetamol", "Fever reducer", MedType.BASIC, LocalDate.of(2025, 10, 10));
        when(medicineRepository.save(medicine)).thenReturn(medicine);

        //When
        Medicine result = medicineService.saveMedicine(medicine);

        //Then
        assertEquals(medicine, result);
        verify(medicineRepository, times(1)).save(medicine);
    }

    @Test
    public void testGetMedicine() throws MedicineNotFoundException {
        //Given
        Medicine medicine = new Medicine(4L, "Aspirin", "Painkiller", MedType.BASIC, LocalDate.of(2025, 9, 9));
        when(medicineRepository.findById(4L)).thenReturn(Optional.of(medicine));

        //When
        Medicine result = medicineService.getMedicine(4L);

        //Then
        assertEquals(medicine, result);
        verify(medicineRepository, times(1)).findById(4L);
    }

    @Test
    public void testGetMedicineThrowsException() {
        //Given
        when(medicineRepository.findById(5L)).thenReturn(Optional.empty());

        //When and then
        assertThrows(MedicineNotFoundException.class, () -> medicineService.getMedicine(5L));
        verify(medicineRepository, times(1)).findById(5L);
    }

    @Test
    public void testDeleteMedicine() {
        //Given
        doNothing().when(medicineRepository).deleteById(6L);

        //When
        medicineService.deleteMedicine(6L);

        //Then
        verify(medicineRepository, times(1)).deleteById(6L);
    }
}