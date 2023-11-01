package com.example.rivaconceptproject.domain.User;

import com.example.rivaconceptproject.domain.Reservation.Reservation;
import com.example.rivaconceptproject.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    private List<Reservation> reservation; //List of reservations
}
