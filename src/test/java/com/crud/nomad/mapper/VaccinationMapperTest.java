package com.crud.nomad.mapper;

import com.crud.nomad.domain.NomadUser;
import com.crud.nomad.domain.Vaccination;
import com.crud.nomad.domain.dto.VaccinationDto;
import com.crud.nomad.domain.enums.VacType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class VaccinationMapperTest {
    private VaccinationMapper vaccinationMapper;
    @BeforeEach
    void setUp() {
        vaccinationMapper = new VaccinationMapper();
    }

    @Test
    void testMapToVaccinationDto() {
        //Given
    Vaccination vaccination = new Vaccination(1L, "Disease",  LocalDate.of(2000, 12, 12), VacType.COMPLETED, new NomadUser());

        //When
    VaccinationDto vaccinationDto = vaccinationMapper.mapToVaccinationDto(vaccination);

        //Then
    assertEquals(vaccination.getVacId(), vaccinationDto.getVacId());
    assertEquals(vaccination.getDiseaseName(), vaccinationDto.getDiseaseName());
    assertEquals(vaccination.getLastVac(), vaccinationDto.getLastVac());
    assertEquals(vaccination.getVacType(), vaccinationDto.getVacType());
    }

    @Test
    void testMapToVaccination() {
        //Given
    VaccinationDto vaccinationDto = new VaccinationDto(1L, "Disease",  LocalDate.of(2000, 12, 12), VacType.COMPLETED);

        //When
    Vaccination vaccination = vaccinationMapper.mapToVaccination(vaccinationDto);

        //Then
        assertEquals(vaccinationDto.getVacId(), vaccination.getVacId());
        assertEquals(vaccinationDto.getDiseaseName(), vaccination.getDiseaseName());
        assertEquals(vaccinationDto.getLastVac(), vaccination.getLastVac());
        assertEquals(vaccinationDto.getVacType(), vaccination.getVacType());
    }

    @Test
    void testMapToVaccinationDtoList() {
        //Given
        Vaccination vaccination1 = new Vaccination(1L, "Disease",  LocalDate.of(2000, 12, 12), VacType.COMPLETED, new NomadUser());
        Vaccination vaccination2 = new Vaccination(1L, "Disease",  LocalDate.of(2000, 12, 12), VacType.COMPLETED, new NomadUser());
        List<Vaccination> vaccinationsList = new ArrayList<>();
        vaccinationsList.add(vaccination1);
        vaccinationsList.add(vaccination2);

        //When
        List<VaccinationDto> vaccinationDtoList = vaccinationMapper.mapToVaccinationDtoList(vaccinationsList);

        //Then
        assertEquals(vaccinationsList.size(), vaccinationDtoList.size());
    }
}