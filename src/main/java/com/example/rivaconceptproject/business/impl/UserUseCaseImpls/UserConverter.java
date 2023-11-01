package com.example.rivaconceptproject.business.impl.UserUseCaseImpls;

import com.example.rivaconceptproject.domain.User.User;
import com.example.rivaconceptproject.persistence.entity.UserEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserConverter {


    public static User convert(UserEntity user){
//        Reservation reservation = Reservation.builder()
//                .reservationId(user.getReservation().getReservationId())
//                .userId(user.getReservation().getUserId())
//                .eventType(user.getReservation().getEventType())
//                .reservationCreatedDate(user.getReservation().getReservationCreatedDate())
//                .reservationDate(user.getReservation().getReservationDate())
//                .startTime(user.getReservation().getStartTime())
//                .endTime(user.getReservation().getEndTime())
//                .build();

        return User.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .password(user.getPassword())
                .role(user.getRole())
//                .reservation((List<Reservation>) user.getReservation())
                .build();
        //.birthDate(user.getBirthDate())
        //.reservation(user.getReservation())
        //.reservation(ReservationConverter.convert(user.getReservation()))
    }
}
