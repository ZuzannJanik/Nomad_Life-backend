package com.crud.nomad.service;

import com.crud.nomad.domain.NomadUser;
import com.crud.nomad.exceptions.NomadUserNotFoundException;
import com.crud.nomad.respository.NomadUserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class NomadNomadUserServiceTest {
    @Mock
    private NomadUserRepository nomadUserRepository;
    @InjectMocks
    private NomadUserService nomadUserService;

    @Test
    public void testGetAllUsers() {
        //Given
        NomadUser nomadUser1 = new NomadUser(1L, "Adam", "Nowak", "Poland", "Login","Haslo","USER", new HashSet<>(), new ArrayList<>());
        NomadUser nomadUser2 = new NomadUser(2L, "Ewa", "Kowalska", "Poland", "Login","Haslo","USER", new HashSet<>(), new ArrayList<>());
        List<NomadUser> nomadUsers = Arrays.asList(nomadUser1, nomadUser2);
        when(nomadUserRepository.findAll()).thenReturn(nomadUsers);

        //When
        List<NomadUser> result = nomadUserService.getAllNomadUsers();

        //Then
        assertEquals(nomadUsers, result);
        verify(nomadUserRepository, times(1)).findAll();
    }

    @Test
    public void testSaveUser() {
        //Given
        NomadUser nomadUser = new NomadUser(3L, "Jan", "Zielinski", "Poland", "Login","Haslo","USER", new HashSet<>(), new ArrayList<>());
        when(nomadUserRepository.save(nomadUser)).thenReturn(nomadUser);

        //When
        NomadUser result = nomadUserService.saveNomadUser(nomadUser);

        //Then
        assertEquals(nomadUser, result);
        verify(nomadUserRepository, times(1)).save(nomadUser);
    }

    @Test
    public void testGetUser() throws NomadUserNotFoundException {
        //Given
        NomadUser nomadUser = new NomadUser(4L, "Anna", "Nowakowska", "Poland", "Login","Haslo","USER", new HashSet<>(), new ArrayList<>());
        when(nomadUserRepository.findById(4L)).thenReturn(Optional.of(nomadUser));

        //When
        NomadUser result = nomadUserService.getNomadUser(4L);

        //Then
        assertEquals(nomadUser, result);
        verify(nomadUserRepository, times(1)).findById(4L);
    }

    @Test
    public void testGetUserThrowsException() {
        //Given
        when(nomadUserRepository.findById(5L)).thenReturn(Optional.empty());

        //When and then
        assertThrows(NomadUserNotFoundException.class, () -> nomadUserService.getNomadUser(5L));
        verify(nomadUserRepository, times(1)).findById(5L);
    }

    @Test
    public void testDeleteUser() {
        //Given
        doNothing().when(nomadUserRepository).deleteById(6L);

        //When
        nomadUserService.deleteNomadUser(6L);

        //Then
        verify(nomadUserRepository, times(1)).deleteById(6L);
    }
}