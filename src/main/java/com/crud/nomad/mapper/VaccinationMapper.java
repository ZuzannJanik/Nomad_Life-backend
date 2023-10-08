package com.crud.nomad.mapper;

import com.crud.nomad.domain.User;
import com.crud.nomad.domain.Vaccination;
import com.crud.nomad.domain.dto.UserDto;
import com.crud.nomad.domain.dto.VaccinationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VaccinationMapper {
    @Autowired
    UserMapper userMapper;

    public Vaccination mapToVaccination(final VaccinationDto vaccinationDto) {
        return Vaccination.builder()
                .vacId(vaccinationDto.getVacId())
                .diseaseName(vaccinationDto.getDiseaseName())
                .lastVac(vaccinationDto.getLastVac())
                .vacType(vaccinationDto.getVacType())
                .user(userMapper.mapToUser(vaccinationDto.getUserDto()))
                .build();
    }
    public VaccinationDto mapToVaccinationDto(final Vaccination vaccination) {
                VaccinationDto vaccinationDto = new VaccinationDto();
                vaccinationDto.setVacId(vaccination.getVacId());
                vaccinationDto.setDiseaseName(vaccination.getDiseaseName());
                vaccinationDto.setLastVac(vaccination.getLastVac());
                vaccinationDto.setVacType(vaccination.getVacType());
                UserMapper userMapper = new UserMapper();
                UserDto userDto = userMapper.mapToUserDto(vaccination.getUser());
                vaccinationDto.setUserDto(userDto);
                return vaccinationDto;
            }


        public List<VaccinationDto> mapToVaccinationDtoList(final List<Vaccination> vaccinationList){
            return vaccinationList.stream()
                    .map(this::mapToVaccinationDto)
                    .toList();
        }
}
