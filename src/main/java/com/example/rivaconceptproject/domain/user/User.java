package com.example.rivaconceptproject.domain.user;

import com.example.rivaconceptproject.domain.enums.Role;
import com.example.rivaconceptproject.domain.reservation.Reservation;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

//    @JsonIgnore
    private String password;

//    @JsonIgnore
    private Role role;

    private boolean isActive;

//    @JsonIgnore
    private List<Reservation> reservation; //List of reservations
}
