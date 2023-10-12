package com.crud.nomad.mapper;

import com.crud.nomad.domain.User;
import com.crud.nomad.domain.Vaccination;
import com.crud.nomad.domain.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserMapperTest {
    private  UserMapper userMapper;

    @BeforeEach
    void setUp() {
        userMapper = new UserMapper();
    }

    @Test
    void testMapToUser() {
        //Given
        User user = new User(1L, "1Name", "2Name", "Poland", new HashSet<>(),new ArrayList<>());

        //When
        UserDto userDto = userMapper.mapToUserDto(user);

        //Then
        assertEquals(user.getUserId(), userDto.getUserId());
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getSurname(), userDto.getSurname());
        assertEquals(user.getHomeland(), userDto.getHomeland());
    }

    @Test
    void mapToUserDto() {
        //Given
        UserDto userDto = new UserDto(1L, "1Name", "2Name", "Poland", new HashSet<>(), new ArrayList<>());

        //When
        User user = userMapper.mapToUser(userDto);

        //Then
        assertEquals(userDto.getUserId(), user.getUserId());
        assertEquals(userDto.getFirstName(), user.getFirstName());
        assertEquals(userDto.getSurname(), user.getSurname());
        assertEquals(userDto.getHomeland(), user.getHomeland());
    }

    @Test
    void mapToUserDtoList() {
      //Given
        User user1 = new User(1L, "1Name", "2Name", "Poland", new HashSet<>(), new ArrayList<>());
        User user2 = new User(2L, "3Name", "4Name", "Hong Kong", new HashSet<>(), new ArrayList<>());
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        //When
        List<UserDto> userDtoList = userMapper.mapToUserDtoList(userList);

        //Then
        assertEquals(userList.size(), userDtoList.size());
    }
}
