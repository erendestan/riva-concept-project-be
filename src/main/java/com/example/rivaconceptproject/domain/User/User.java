package com.example.rivaconceptproject.domain.User;

import com.example.rivaconceptproject.domain.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private Role role;

//    @JsonIgnore
//    private List<Reservation> reservation; //List of reservations
}
