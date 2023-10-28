package com.crud.nomad.domain.dto;

import com.crud.nomad.domain.Trip;
import com.crud.nomad.domain.Vaccination;
import com.crud.nomad.domain.enums.UserRole;
import lombok.*;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class NomadUserDto {
    private Long userId;
    private String firstName;
    private String surname;
    private String homeland;
    private String login;
    private String password;
    private UserRole role;
    private Set<Trip> tripList;
    private List<Vaccination> vaccinationList;
}
