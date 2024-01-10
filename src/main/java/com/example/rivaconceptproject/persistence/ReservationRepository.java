package com.example.rivaconceptproject.persistence;

import com.example.rivaconceptproject.domain.enums.Event;
import com.example.rivaconceptproject.persistence.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    //Derived Query (@Query is not necessary because Spring Data JPA will automatically generate the query based on the method name)
    boolean existsByReservationDate(LocalDateTime reservationDate);

    //Derived Query
    Optional<ReservationEntity> findByReservationId(long reservationId);

    //Parameterized Query
    @Query("SELECT r FROM ReservationEntity r WHERE r.reservationDate = :reservationDate")
    Optional<ReservationEntity> findByReservationDate(LocalDateTime reservationDate);

    @Query("SELECT r FROM ReservationEntity r WHERE (:eventTypeFilter IS NULL OR r.eventType = :eventTypeFilter) " +
            "AND (:startDateFilter IS NULL OR r.reservationDate >= :startDateFilter) " +
            "AND (:endDateFilter IS NULL OR r.reservationDate <= :endDateFilter)")
    Optional<List<ReservationEntity>> findFilteredReservations(
            @Param("eventTypeFilter") Event eventTypeFilter,
            @Param("startDateFilter") LocalDateTime startDateFilter,
            @Param("endDateFilter") LocalDateTime endDateFilter
    );

}
