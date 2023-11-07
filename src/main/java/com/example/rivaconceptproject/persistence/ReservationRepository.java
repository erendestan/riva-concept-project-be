package com.example.rivaconceptproject.persistence;

import com.example.rivaconceptproject.persistence.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    boolean existsByDateTime(LocalDateTime reservationDateTime);

    List<ReservationEntity> findAllReservations();

    ReservationEntity save(ReservationEntity reservation);

    void deleteByReservationId(long reservationId);

    Optional<ReservationEntity> findReservationById(long reservationId);
    Optional<ReservationEntity> findReservationByUserId(long userId);

    long count();

    public void clear();
}
