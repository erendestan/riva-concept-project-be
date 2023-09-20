package com.example.rivaconceptproject.persistence.entity;

import com.example.rivaconceptproject.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    private Long id;
    private String firstName;

    private String lastName;

    private String email;
    private Long phoneNumber;

    private Date birthDate;

    private Role role;
}
