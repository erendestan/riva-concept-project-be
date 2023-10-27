package com.example.rivaconceptproject.business.ReservationUseCases;

import com.example.rivaconceptproject.domain.Reservation.Reservation;

import java.util.Optional;

public interface GetReservationUseCase {
    Optional<Reservation> getReservation(long reservationId);

    Optional<Reservation> getReservationByUserId(long userId);
}
