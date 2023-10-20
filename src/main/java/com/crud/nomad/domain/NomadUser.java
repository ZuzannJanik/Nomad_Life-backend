package com.crud.nomad.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import jakarta.persistence.*;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "firstname")
    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("surname")
    @Column(name = "surname")
    private String surname;

    @JsonProperty("homeland")
    @Column(name = "homeland")
    private String homeland;

    @JsonProperty("login")
    @Column(name = "login")
    private String login;

    @JsonProperty("password")
    @Column(name="password")
    private String password;

    @JsonProperty("role")
    @Column(name="role")
    private String role;

    @ManyToMany(targetEntity = Trip.class,
            mappedBy = "userList",
            cascade = {CascadeType.DETACH,CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Trip> tripList = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Vaccination> vaccinationList = new ArrayList<>();

    public User (Long userId, String firstName, String surname, String homeland, String login, String password, String role) {
        this.userId = userId;
        this.firstName = firstName;
        this.surname = surname;
        this.homeland = homeland;
        this.login = login;
        this.password = password;
        this.role = role;
    }
}
