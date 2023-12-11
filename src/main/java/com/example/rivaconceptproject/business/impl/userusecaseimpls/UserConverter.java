package com.example.rivaconceptproject.business.impl.userusecaseimpls;

import com.example.rivaconceptproject.business.impl.reservationusecaseimpls.ReservationConverter;
import com.example.rivaconceptproject.domain.user.User;
import com.example.rivaconceptproject.persistence.entity.UserEntity;

public class UserConverter {

    private UserConverter() {
        // private constructor to hide the implicit public one
    }
    public static User convert(UserEntity user){
        return User.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .password(user.getPassword())
                .role(user.getRole())
                .reservation(user.getReservation().stream().map(ReservationConverter::convert).toList())
                .isActive(user.isActive())
                .build();
    }
}
