package com.crud.nomad.domain;

import com.crud.nomad.domain.enums.TripStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "trips")
@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "trip_id")
    private Long tripId;

    @Column(name = "date_start")
    @JsonProperty("data_start")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateStart;

    @Column(name = "date_end")
    @JsonProperty("data_end")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateEnd;

    @Column(name = "destination_country")
    @JsonProperty("destination")
    private String destinationCountry;

    @Column(name = "trip_status")
    @Enumerated(EnumType.STRING)
    @JsonProperty("status")
    private TripStatus tripStatus;

    @ManyToMany
    @JoinTable(
            name = "join_trip_user",
            joinColumns = {
                    @JoinColumn(name = "trip_id", referencedColumnName = "trip_id", foreignKey = @ForeignKey(name = "fk_trip_id"))},
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "user_id", foreignKey = @ForeignKey(name = "fk_user_id"))}
    )
    @Builder.Default
    private Set<NomadUser> nomadUserList = new HashSet<>();

    public Trip (Long tripId, LocalDate dateStart, LocalDate dateEnd, String destinationCountry, TripStatus tripStatus) {
        this.tripId = tripId;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.destinationCountry = destinationCountry;
        this.tripStatus = tripStatus;
    }

}



