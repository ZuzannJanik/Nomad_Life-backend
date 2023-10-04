package com.crud.nomad.mapper;

import com.crud.nomad.domain.Trip;
import com.crud.nomad.domain.dto.TripDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripMapper {
    public Trip mapToTrip(final TripDto tripDto) {
        return Trip.builder()
                .tripId(tripDto.getTripId())
                .dateStart(tripDto.getDateStart())
                .dateEnd(tripDto.getDateEnd())
                .destinationCountry(tripDto.getDestinationCountry())
                .tripStatus(tripDto.getTripStatus())
                .build();
    }
    public TripDto mapToTripDto(final Trip trip) {
        TripDto tripDto = new TripDto();
                trip.setTripId(trip.getTripId());
                trip.setDateStart(trip.getDateStart());
                trip.setDateEnd(trip.getDateEnd());
                trip.setDestinationCountry(trip.getDestinationCountry());
                trip.setTripStatus(trip.getTripStatus());
                trip.setUserList(trip.getUserList());
        return tripDto;
    }
    public List<TripDto> mapToTripDtoList(final List<Trip> tripList){
        return tripList.stream()
                .map(this::mapToTripDto)
                .toList();
    }
}
