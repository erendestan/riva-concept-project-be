package com.example.rivaconceptproject.business.impl.ReservationUseCaseImpls;

import com.example.rivaconceptproject.business.ReservationUseCases.GetReservationUseCase;
import com.example.rivaconceptproject.domain.Reservation.Reservation;

import java.util.Optional;

public class GetReservationUseCaseImpl implements GetReservationUseCase {
    @Override
    public Optional<Reservation> getReservation(long reservationId) {
        return Optional.empty();
    }
}
