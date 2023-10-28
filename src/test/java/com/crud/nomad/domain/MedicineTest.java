package com.crud.nomad.domain;

import com.crud.nomad.domain.enums.MedType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MedicineTest {
    private Medicine medicine;
    @BeforeEach
    void setUp() {
        medicine = new Medicine(1L, "Aspirin", "Head pain", MedType.BASIC, LocalDate.of(2023, 12, 31));
    }

    @Test
    void getMedicineId() {
        //Then
        assertEquals(1L, medicine.getMedicineId());
    }

    @Test
    void getMedicineName() {
        //Then
        assertEquals("Aspirin", medicine.getMedicineName());
    }

    @Test
    void getDesignation() {
        //Then
        assertEquals("Head pain", medicine.getDesignation());
    }

    @Test
    void getMedType() {
        //Then
        assertEquals(MedType.BASIC, medicine.getMedType());
    }

    @Test
    void getExpiryDate() {
        //Then
        assertEquals(LocalDate.of(2023, 12, 31), medicine.getExpiryDate());
    }

    @Test
    void setMedicineName() {
        //When
        medicine.setMedicineName("Ibuprofen");

        //Then
        assertEquals("Ibuprofen", medicine.getMedicineName());
    }

    @Test
    void setDesignation() {
        //When
        medicine.setDesignation("Head pain");

        //Then
        assertEquals("Head pain", medicine.getDesignation());
    }

    @Test
    void setMedType() {
        //When
        medicine.setMedType(MedType.FIRST_AID);

        //Then
        assertEquals(MedType.FIRST_AID, medicine.getMedType());
    }

    @Test
    void setExpiryDate() {
        //When
        medicine.setExpiryDate(LocalDate.of(2024, 1, 1));

        //Then
        assertEquals(LocalDate.of(2024, 1, 1), medicine.getExpiryDate());
    }
}