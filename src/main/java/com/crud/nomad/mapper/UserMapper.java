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
                UserDto userDto = new UserDto();
                userDto.setUserId(user.getUserId());
                userDto.setFirstName(user.getFirstName());
                userDto.setSurname(user.getSurname());
                userDto.setHomeland(user.getHomeland());
                userDto.setTripList(user.getTripList());
                userDto.setVaccinations(user.getVaccinations());
                return userDto;
            }

        public List<UserDto> mapToUserDtoList(final List<User> userList){
            return userList.stream()
                    .map(this::mapToUserDto)
                    .toList();
        }
    }