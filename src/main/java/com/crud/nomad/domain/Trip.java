package com.crud.nomad.domain;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "trips")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_id", unique = true, nullable = false)
    private Long tripId;

    @Column(name = "date_start")
    private LocalDate dateStart;

    @Column(name = "date_end")
    private LocalDate dateEnd;

    @Column(name = "destination_country")
    private String destinationCountry;

    @Column(name = "trip_status")
    @Enumerated(EnumType.STRING)
    private TripStatus tripStatus;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "trips_users",
            joinColumns = {@JoinColumn(name = "trip_id", referencedColumnName = "trip_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")}
    )

    @Builder.Default
    private Set<User> userList = new HashSet<>();
    public void addUser(User user) {
        this.userList.add(user);
        user.getTripList().add(this);
    }
    public void removeUser(User user) {
        this.userList.remove(user);
        user.getTripList().remove(this);
    }
    public void updateStatus() {
        if (dateStart.isAfter(LocalDate.now())) {
            setTripStatus(TripStatus.PLANNED);
        } else if (dateStart.isBefore(LocalDate.now()) && dateEnd.isAfter(LocalDate.now())) {
            setTripStatus(TripStatus.ACTIVE);
        } else if (dateEnd.isBefore(LocalDate.now())){
            setTripStatus(TripStatus.ENDED);
        }
    }
    public Trip (Long tripId, LocalDate dateStart, LocalDate dateEnd, String destinationCountry, TripStatus tripStatus) {
        this.tripId = tripId;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.destinationCountry = destinationCountry;
        this.tripStatus = tripStatus;
    }

}



