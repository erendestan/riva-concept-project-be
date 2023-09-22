package com.example.rivaconceptproject.domain;


import com.example.rivaconceptproject.domain.enums.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
//    @NotNull
//    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String email;

    @NotNull
    private Long phoneNumber;

//    @NotNull
//    @DateTimeFormat( pattern="yyyy-MM-dd")
//    private Date birthDate;

    @NotNull
    private Role role;


}
