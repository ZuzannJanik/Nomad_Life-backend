package com.crud.nomad.mapper;

import com.crud.nomad.domain.NomadUser;
import com.crud.nomad.domain.dto.NomadUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NomadUserMapper {
    public NomadUser mapToUser(final NomadUserDto userDto) {
        return NomadUser.builder()
                .userId(userDto.getUserId())
                .firstName(userDto.getFirstName())
                .surname(userDto.getSurname())
                .homeland(userDto.getHomeland())
                .login(userDto.getLogin())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .tripList(userDto.getTripList())
                .vaccinationList(userDto.getVaccinationList())
                .build();
    }

    public NomadUserDto mapToUserDto(final NomadUser nomadUser) {
        return NomadUserDto.builder()
                .userId(nomadUser.getUserId())
                .firstName(nomadUser.getFirstName())
                .surname(nomadUser.getSurname())
                .homeland(nomadUser.getHomeland())
                .login(nomadUser.getLogin())
                .password(nomadUser.getPassword())
                .role(nomadUser.getRole())
                .tripList(nomadUser.getTripList())
                .vaccinationList(nomadUser.getVaccinationList())
                .build();
    }

    public List<NomadUserDto> mapToUserDtoList(final List<NomadUser> nomadUserList) {
        return nomadUserList.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}