package com.example.rivaconceptproject.business.impl.reservationusecaseimpls;

import com.example.rivaconceptproject.domain.reservation.Reservation;
import com.example.rivaconceptproject.domain.user.User;
import com.example.rivaconceptproject.persistence.entity.ReservationEntity;
import com.example.rivaconceptproject.persistence.entity.UserEntity;

public class ReservationConverter {

    private ReservationConverter(){
        // private constructor to hide the implicit public one
    }
    public static User userConvert(UserEntity user){
        return User.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .password(user.getPassword())
                .role(user.getRole())
                .isActive(user.isActive())
                .build();
    }

    public static Reservation convert(ReservationEntity reservation){
        return Reservation.builder()
                .reservationId(reservation.getReservationId())
                .user(userConvert(reservation.getUser()))
                .eventType(reservation.getEventType())
                .reservationCreatedDate(reservation.getReservationCreatedDate())
                .reservationDate(reservation.getReservationDate())
                .startTime(reservation.getStartTime())
                .endTime(reservation.getEndTime())
                .build();
    }
}
