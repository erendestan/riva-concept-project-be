package com.example.rivaconceptproject.business.reservationusecases;

import com.example.rivaconceptproject.domain.reservation.Reservation;
import com.example.rivaconceptproject.persistence.entity.ReservationEntity;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface GetReservationUseCase {
    Optional<Reservation> getReservation(long reservationId);

    Optional<Reservation> getReservationByUserId(long userId);

    @Transactional
    Optional<ReservationEntity> findByReservationDate(LocalDateTime reservationDate);
}
