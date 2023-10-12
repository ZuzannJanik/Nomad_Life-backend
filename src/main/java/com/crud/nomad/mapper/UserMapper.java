package com.crud.nomad.mapper;

import com.crud.nomad.domain.User;
import com.crud.nomad.domain.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserMapper {
    public User mapToUser(final UserDto userDto) {
        return User.builder()
                .userId(userDto.getUserId())
                .firstName(userDto.getFirstName())
                .surname(userDto.getSurname())
                .homeland(userDto.getHomeland())
                .tripList(userDto.getTripList())
                .vaccinationList(userDto.getVaccinationList())
                .build();
    }

    public UserDto mapToUserDto(final User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .surname(user.getSurname())
                .homeland(user.getHomeland())
                .tripList(user.getTripList())
                .vaccinationList(user.getVaccinationList())
                .build();
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList) {
        return userList.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}