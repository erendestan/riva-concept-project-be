package com.example.rivaconceptproject.business.impl.ReservationUseCaseImpls;

import com.example.rivaconceptproject.business.ReservationUseCases.GetReservationsUseCase;
import com.example.rivaconceptproject.domain.Reservation.GetAllReservationsResponse;
import com.example.rivaconceptproject.domain.Reservation.Reservation;
import com.example.rivaconceptproject.persistence.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetReservationsUseCaseImpl implements GetReservationsUseCase {
    private final ReservationRepository reservationRepository;
    @Override
    public GetAllReservationsResponse getReservations() {
        List<Reservation> reservations = reservationRepository.findAllReservations()
                .stream()
                .map(ReservationConverter::convert)
                .toList();

        return GetAllReservationsResponse.builder()
                .reservations(reservations)
                .build();
    }
}
