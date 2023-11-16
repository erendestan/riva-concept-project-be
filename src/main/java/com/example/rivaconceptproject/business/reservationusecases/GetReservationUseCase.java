package com.example.rivaconceptproject.business.reservationusecases;

import com.example.rivaconceptproject.domain.reservation.Reservation;

import java.util.Optional;

public interface GetReservationUseCase {
    Optional<Reservation> getReservation(long reservationId);

    Optional<Reservation> getReservationByUserId(long userId);
}
