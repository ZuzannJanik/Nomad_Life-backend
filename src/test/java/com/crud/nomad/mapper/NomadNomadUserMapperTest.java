package com.crud.nomad.mapper;

import com.crud.nomad.domain.NomadUser;
import com.crud.nomad.domain.dto.NomadUserDto;
import com.crud.nomad.domain.enums.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NomadNomadUserMapperTest {
    private NomadUserMapper nomadUserMapper;
    @BeforeEach
    void setUp() {
        nomadUserMapper = new NomadUserMapper();
    }

    @Test
    void testMapToUserDto() {
        //Given
        NomadUser nomadUser = new NomadUser(1L, "1Name", "2Name", "Poland", "Login","Haslo", UserRole.USER, new HashSet<>(),new ArrayList<>(), new HashSet<>());

        //When
        NomadUserDto userDto = nomadUserMapper.mapToUserDto(nomadUser);

        //Then
        assertEquals(nomadUser.getUserId(), userDto.getUserId());
        assertEquals(nomadUser.getFirstName(), userDto.getFirstName());
        assertEquals(nomadUser.getSurname(), userDto.getSurname());
        assertEquals(nomadUser.getHomeland(), userDto.getHomeland());
    }

    @Test
    void testMapToUser() {
        //Given
        NomadUserDto userDto = new NomadUserDto(1L, "1Name", "2Name", "Poland", "Login","Haslo",UserRole.USER, new HashSet<>(), new ArrayList<>());

        //When
        NomadUser nomadUser = nomadUserMapper.mapToUser(userDto);

        //Then
        assertEquals(userDto.getUserId(), nomadUser.getUserId());
        assertEquals(userDto.getFirstName(), nomadUser.getFirstName());
        assertEquals(userDto.getSurname(), nomadUser.getSurname());
        assertEquals(userDto.getHomeland(), nomadUser.getHomeland());
    }

    @Test
    void testMapToUserDtoList() {
      //Given
        NomadUser nomadUser1 = new NomadUser(1L, "1Name", "2Name", "Poland", "Login","Haslo",UserRole.USER, new HashSet<>(), new ArrayList<>(), new HashSet<>());
        NomadUser nomadUser2 = new NomadUser(2L, "3Name", "4Name", "Hong Kong", "Login","Haslo",UserRole.USER, new HashSet<>(), new ArrayList<>(), new HashSet<>());
        List<NomadUser> nomadUserList = new ArrayList<>();
        nomadUserList.add(nomadUser1);
        nomadUserList.add(nomadUser2);

        //When
        List<NomadUserDto> userDtoList = nomadUserMapper.mapToUserDtoList(nomadUserList);

        //Then
        assertEquals(nomadUserList.size(), userDtoList.size());
    }
}
