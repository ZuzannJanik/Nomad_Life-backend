package com.crud.nomad.domain.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;
@AllArgsConstructor
@Getter
public class UserDto {
    private Long userId;
    private String firstName;
    private String surname;


}
