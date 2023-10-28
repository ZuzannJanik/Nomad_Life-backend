package com.crud.nomad.domain;

import com.crud.nomad.domain.enums.UserRole;
import jakarta.validation.constraints.NotNull;
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
    private String firstName;

    @Column(name = "surname")
    private String surname;

    @NotNull
    @Column(name = "homeland")
    private String homeland;

    @NotNull
    @Column(name = "login")
    private String login;

    @NotNull
    @Column(name="password")
    private String password;

    @Column(name="role")
    private UserRole role;

    @ManyToMany(targetEntity = Trip.class,
            mappedBy = "nomadUserList",
            cascade = {CascadeType.DETACH,CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Trip> tripList = new HashSet<>();

    @OneToMany(mappedBy = "nomadUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Vaccination> vaccinationList = new ArrayList<>();

    @ManyToMany(targetEntity = Medicine.class,
            mappedBy = "userList",
            cascade = {CascadeType.DETACH,CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Medicine> medicineList = new HashSet<>();

    public NomadUser(Long userId, String firstName, String surname, String homeland, String login, String password, UserRole role) {
        this.userId = userId;
        this.firstName = firstName;
        this.surname = surname;
        this.homeland = homeland;
        this.login = login;
        this.password = password;
        this.role = role;
    }
}
