package com.example.rivaconceptproject.business.impl.ReservationUseCaseImpls;

import com.example.rivaconceptproject.business.impl.UserUseCaseImpls.UserConverter;
import com.example.rivaconceptproject.domain.Reservation.Reservation;
import com.example.rivaconceptproject.persistence.entity.ReservationEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ReservationConverter {

    //ReservationEntity to reservation
    public static Reservation convert(ReservationEntity reservation){
        return Reservation.builder()
                .reservationId(reservation.getReservationId())
                .user(UserConverter.convert(reservation.getUser()))
//                .userId(reservation.getUserId())
                .eventType(reservation.getEventType())
                .reservationCreatedDate(reservation.getReservationCreatedDate())
                .reservationDate(reservation.getReservationDate())
                .startTime(reservation.getStartTime())
                .endTime(reservation.getEndTime())
                .build();
    }
}
