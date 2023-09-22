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
        TripDto tripDto = new TripDto(
                trip.getTripId(),
                trip.getDateStart(),
                trip.getDateEnd(),
                trip.getDestinationCountry(),
                trip.getTripStatus());
        return tripDto;
    }
    public List<TripDto> mapToTripDtoList(final List<Trip> tripList){
        return tripList.stream()
                .map(this::mapToTripDto)
                .toList();
    }
}
