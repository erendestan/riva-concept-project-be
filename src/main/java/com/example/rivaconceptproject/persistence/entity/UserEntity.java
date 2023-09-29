package com.example.rivaconceptproject.persistence.entity;

import com.example.rivaconceptproject.domain.enums.Role;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class UserEntity {
    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private long phoneNumber;

    private Role role;
}
