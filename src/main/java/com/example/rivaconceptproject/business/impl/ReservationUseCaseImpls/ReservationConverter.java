package com.example.rivaconceptproject.business.impl.ReservationUseCaseImpls;

import com.example.rivaconceptproject.domain.Reservation.Reservation;
import com.example.rivaconceptproject.persistence.entity.ReservationEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ReservationConverter {

    public static Reservation convert(ReservationEntity reservation){
        return Reservation.builder()
                .reservationId(reservation.getReservationId())
                .userId(reservation.getUserId())
                .eventType(reservation.getEventType())
                .reservationCreatedDate(reservation.getReservationCreatedDate())
                .reservationDate(reservation.getReservationDate())
                .startTime(reservation.getStartTime())
                .endTime(reservation.getEndTime())
                .build();
    }
}
