package com.example.rivaconceptproject.business.impl.ReservationUseCaseImpls;

import com.example.rivaconceptproject.business.impl.UserUseCaseImpls.UserConverter;
import com.example.rivaconceptproject.domain.Reservation.Reservation;
import com.example.rivaconceptproject.persistence.entity.ReservationEntity;

public class ReservationConverter {

    private ReservationConverter(){
        // private constructor to hide the implicit public one
    }

    public static Reservation convert(ReservationEntity reservation){
        return Reservation.builder()
                .reservationId(reservation.getReservationId())
                .user(UserConverter.convert(reservation.getUser()))
                .eventType(reservation.getEventType())
                .reservationCreatedDate(reservation.getReservationCreatedDate())
                .reservationDate(reservation.getReservationDate())
                .startTime(reservation.getStartTime())
                .endTime(reservation.getEndTime())
                .build();
    }
}
