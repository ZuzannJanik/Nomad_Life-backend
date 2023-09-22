package com.crud.nomad.mapper;

import com.crud.nomad.domain.User;
import com.crud.nomad.domain.Trip;
import com.crud.nomad.domain.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserMapper {

    public User mapToUser(final UserDto userDto){
        return User.builder()
                .userId(userDto.getUserId())
                .firstName(userDto.getFirstName())
                .surname(userDto.getSurname())
                .homeland(userDto.getHomeland())
                .build();
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getUserId(),
                user.getFirstName(),
                user.getSurname(),
                user.getHomeland(),
                user.getTripList().stream()
                        .map(Trip::getTripId)
                        .collect(Collectors.toSet()));
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList){
        return userList.stream()
                .map(this::mapToUserDto)
                .toList();
    }
}

