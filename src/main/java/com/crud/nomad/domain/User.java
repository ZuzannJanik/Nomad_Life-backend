package com.crud.nomad.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long userId;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "surname")
    private String surname;


}
