package com.crud.nomad.service;

import com.crud.nomad.domain.NomadUser;
import com.crud.nomad.domain.Vaccination;
import com.crud.nomad.domain.enums.VacType;
import com.crud.nomad.exceptions.VaccinationNotFoundException;
import com.crud.nomad.respository.VaccinationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class VaccinationServiceTest {

    @Mock
    private VaccinationRepository vaccinationRepository;

    @InjectMocks
    private VaccinationService vaccinationService;

    @Test
    public void testGetAllVaccinations() {
        //Given
        NomadUser nomadUser1 = new NomadUser(1L, "Adam", "Nowak", "Poland", "Login","Haslo","USER", new HashSet<>(), new ArrayList<>());
        NomadUser nomadUser2 = new NomadUser(2L, "Ewa", "Kowalska", "Poland", "Login","Haslo","USER", new HashSet<>(), new ArrayList<>());
        Vaccination vaccination1 = new Vaccination(1L, "Disease1", LocalDate.of(2023, 1, 1), VacType.COMPLETED, nomadUser1);
        Vaccination vaccination2 = new Vaccination(2L, "Disease2", LocalDate.of(2023, 2, 1), VacType.COMPLETED, nomadUser2);
        List<Vaccination> vaccinations = Arrays.asList(vaccination1, vaccination2);
        when(vaccinationRepository.findAll()).thenReturn(vaccinations);

        //When
        List<Vaccination> result = vaccinationService.getAllVaccinations();

        //Then
        assertEquals(vaccinations, result);
        verify(vaccinationRepository, times(1)).findAll();
    }

    @Test
    public void testSaveVaccination() {
        //Given
        NomadUser nomadUser = new NomadUser(3L, "Jan", "Zielinski", "Poland", "Login","Haslo", "USER", new HashSet<>(), new ArrayList<>());
        Vaccination vaccination = new Vaccination(3L, "Disease3", LocalDate.of(2023, 3, 1), VacType.REPEATABLE, nomadUser);
        when(vaccinationRepository.save(vaccination)).thenReturn(vaccination);

        //When
        Vaccination result = vaccinationService.saveVaccination(vaccination);

        //Then
        assertEquals(vaccination, result);
        verify(vaccinationRepository, times(1)).save(vaccination);
    }

    @Test
    public void testGetVaccination() throws VaccinationNotFoundException {
        //Given
        NomadUser nomadUser = new NomadUser(4L, "Anna", "Nowakowska", "Poland", "Login","Haslo","USER", new HashSet<>(), new ArrayList<>());
        Vaccination vaccination = new Vaccination(4L, "Disease4", LocalDate.of(2023, 4, 1), VacType.UNCOMPLETED, nomadUser);
        when(vaccinationRepository.findById(4L)).thenReturn(Optional.of(vaccination));

        //When
        Vaccination result = vaccinationService.getVaccination(4L);

        //Then
        assertEquals(vaccination, result);
        verify(vaccinationRepository, times(1)).findById(4L);
    }

    @Test
    public void testGetVaccinationThrowsException() {
        //Given
        when(vaccinationRepository.findById(5L)).thenReturn(Optional.empty());

        //When and then
        assertThrows(VaccinationNotFoundException.class, () -> vaccinationService.getVaccination(5L));
        verify(vaccinationRepository, times(1)).findById(5L);
    }

    @Test
    public void testDeleteVaccination() {
        //Given
        doNothing().when(vaccinationRepository).deleteById(6L);

        //When
        vaccinationService.deleteVaccination(6L);

        //Then
        verify(vaccinationRepository, times(1)).deleteById(6L);
    }
}