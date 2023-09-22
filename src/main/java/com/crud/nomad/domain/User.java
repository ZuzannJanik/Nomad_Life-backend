package com.crud.nomad.domain;

import lombok.*;
import javax.persistence.*;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    private Long userId;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "surname")
    private String surname;

    @Column(name = "homeland")
    private String homeland;

    @ManyToMany(mappedBy = "userList", cascade = {CascadeType.DETACH,CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Trip> tripList = new HashSet<>();
    public void addTrip(Trip trip) {
        this.tripList.add(trip);
        trip.getUserList().add(this);
    }
    public void removeTrip(Trip trip) {
        this.tripList.remove(trip);
        trip.getUserList().remove(this);
    }
    public User (Long userId, String firstName, String surname, String homeland) {
        this.userId = userId;
        this.firstName = firstName;
        this.surname = surname;
        this.homeland = homeland;
    }
}
