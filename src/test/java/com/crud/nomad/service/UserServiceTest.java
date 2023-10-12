package com.crud.nomad.service;

import com.crud.nomad.domain.User;
import com.crud.nomad.domain.Vaccination;
import com.crud.nomad.exceptions.UserNotFoundException;
import com.crud.nomad.respository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetAllUsers() {
        //Given
        User user1 = new User(1L, "Adam", "Nowak", "Poland", new HashSet<>(), new ArrayList<>());
        User user2 = new User(2L, "Ewa", "Kowalska", "Poland", new HashSet<>(), new ArrayList<>());
        List<User> users = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(users);

        //When
        List<User> result = userService.getAllUsers();

        //Then
        assertEquals(users, result);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testSaveUser() {
        //Given
        User user = new User(3L, "Jan", "Zielinski", "Poland", new HashSet<>(), new ArrayList<>());
        when(userRepository.save(user)).thenReturn(user);

        //When
        User result = userService.saveUser(user);

        //Then
        assertEquals(user, result);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testGetUser() throws UserNotFoundException {
        //Given
        User user = new User(4L, "Anna", "Nowakowska", "Poland", new HashSet<>(), new ArrayList<>());
        when(userRepository.findById(4L)).thenReturn(Optional.of(user));

        //When
        User result = userService.getUser(4L);

        //Then
        assertEquals(user, result);
        verify(userRepository, times(1)).findById(4L);
    }

    @Test
    public void testGetUserThrowsException() {
        //Given
        when(userRepository.findById(5L)).thenReturn(Optional.empty());

        //When and then
        assertThrows(UserNotFoundException.class, () -> userService.getUser(5L));
        verify(userRepository, times(1)).findById(5L);
    }

    @Test
    public void testDeleteUser() {
        //Given
        doNothing().when(userRepository).deleteById(6L);

        //When
        userService.deleteUser(6L);

        //Then
        verify(userRepository, times(1)).deleteById(6L);
    }
}