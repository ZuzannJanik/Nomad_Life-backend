package com.crud.nomad.domain;

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
public class NomadUser {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "firstname")
    @JsonProperty("firstName")
    private String firstName;

    @Column(name = "surname")
    @JsonProperty("surname")
    private String surname;

    @Column(name = "homeland")
    @JsonProperty("homeland")
    private String homeland;

    @Column(name = "login")
    @JsonProperty("login")
    private String login;

    @Column(name="password")
    @JsonProperty("password")
    private String password;

    @Column(name="role")
    @JsonProperty("role")
    private String role;

    @ManyToMany(targetEntity = Trip.class,
            mappedBy = "nomadUserList",
            cascade = {CascadeType.DETACH,CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Trip> tripList = new HashSet<>();

    @OneToMany(mappedBy = "nomadUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Vaccination> vaccinationList = new ArrayList<>();

    public NomadUser(Long userId, String firstName, String surname, String homeland, String login, String password, String role) {
        this.userId = userId;
        this.firstName = firstName;
        this.surname = surname;
        this.homeland = homeland;
        this.login = login;
        this.password = password;
        this.role = role;
    }
}
