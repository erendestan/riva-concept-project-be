package com.example.rivaconceptproject.domain;

import com.example.rivaconceptproject.domain.enums.Role;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class UpdateUserRequest {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String email;

    @NotNull
    private Long phoneNumber;

    @NotNull
    @DateTimeFormat( pattern="yyyy-MM-dd")
    private Date birthDate;

    @NotNull
    private Role role;
}
