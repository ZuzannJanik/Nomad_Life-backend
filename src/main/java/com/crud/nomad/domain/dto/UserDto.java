package com.crud.nomad.domain.dto;

import lombok.*;
import java.util.*;

@AllArgsConstructor
@Getter
public class UserDto {
    private Long userId;
    private String firstName;
    private String surname;
    private String homeland;
    private Set<Long> tripList;
}
