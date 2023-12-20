package com.example.rivaconceptproject.persistence;

import com.example.rivaconceptproject.persistence.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    boolean existsByReservationDate(LocalDateTime reservationDate);
    Optional<ReservationEntity> findByReservationId(long reservationId);

    Optional<ReservationEntity> findByReservationDate(LocalDateTime reservationDate);

}
