package com.example.rivaconceptproject.business.impl;

import com.example.rivaconceptproject.domain.User;
import com.example.rivaconceptproject.persistence.entity.UserEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserConverter {

    public static User convert(UserEntity user){
        return User.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .build();
        //.birthDate(user.getBirthDate())
    }
}
