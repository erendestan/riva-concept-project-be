package com.example.rivaconceptproject.domain;

import com.example.rivaconceptproject.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private long phoneNumber;

    private String password;

    private Role role;

}
