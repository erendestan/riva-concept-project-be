package com.example.rivaconceptproject.persistence.impl;

import com.example.rivaconceptproject.persistence.ReservationRepository;
import com.example.rivaconceptproject.persistence.entity.ReservationEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class FakeReservationRepositoryImpl implements ReservationRepository {

    private long NEXT_ID = 1;

    private final List<ReservationEntity> savedReservations;

    public FakeReservationRepositoryImpl(){
        this.savedReservations = new ArrayList<>();
    }
    @Override
    public boolean existsByDateTime(LocalDateTime reservationDateTime) {
        return this.savedReservations
                .stream()
                .anyMatch(reservationEntity -> reservationEntity.getReservationDate() == reservationDateTime);
    }

    @Override
    public List<ReservationEntity> findAllReservations() {
        return Collections.unmodifiableList(this.savedReservations);
    }

    @Override
    public ReservationEntity save(ReservationEntity reservation) {
        if(reservation.getReservationId() == 0){
            reservation.setReservationId(NEXT_ID);
            NEXT_ID++;
            this.savedReservations.add(reservation);
        }
        return reservation;
    }

    @Override
    public void deleteByReservationId(long reservationId) {
        this.savedReservations.removeIf(reservationEntity -> reservationEntity.getReservationId() == reservationId);
    }

    @Override
    public Optional<ReservationEntity> findReservationById(long reservationId) {
        for (ReservationEntity reservation : this.savedReservations){
            if(reservation.getReservationId() == reservationId){
                return Optional.of(reservation);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<ReservationEntity> findReservationByUserId(long userId) {
        for(ReservationEntity reservation : this.savedReservations){
            if(reservation.getUser().getId() == userId){
                return Optional.of(reservation);
            }
        }
        return Optional.empty();
    }

    @Override
    public int count() {
        return this.savedReservations.size();
    }

    @Override
    public void clear() {
            savedReservations.clear();
    }
}
