package com.crud.nomad.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "trips")
public class Trip {
    @Id
    @GeneratedValue
    private Long id;


    }
    // private
   // private LocalDate



