package com.example.rivaconceptproject.business.impl.reservationusecaseimpls;

import com.example.rivaconceptproject.business.exception.ReservationRetrivalException;
import com.example.rivaconceptproject.business.reservationusecases.GetFilteredReservationsUseCase;
import com.example.rivaconceptproject.domain.reservation.GetFilteredReservationsRequest;
import com.example.rivaconceptproject.domain.reservation.GetFilteredReservationsResponse;
import com.example.rivaconceptproject.domain.reservation.Reservation;
import com.example.rivaconceptproject.persistence.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetFilteredReservationsUseCaseImpl implements GetFilteredReservationsUseCase {
    private final ReservationRepository reservationRepository;

    @Override
    public GetFilteredReservationsResponse getFilteredReservations(GetFilteredReservationsRequest request) {
        try {
            List<Reservation> filteredReservations = reservationRepository.findFilteredReservations(
                            request.getEventType().toString(),
                            request.getStartDate(),
                            request.getEndDate()
                    ).orElse(List.of()).stream()
                    .map(ReservationConverter::convert)
                    .collect(Collectors.toList());

            return GetFilteredReservationsResponse.builder()
                    .filteredReservations(filteredReservations)
                    .build();
        } catch (Exception ex) {
            // Handle exceptions accordingly (e.g., log the error, throw a custom exception)
            throw new ReservationRetrivalException("Error retrieving filtered reservations");
        }
    }
}
