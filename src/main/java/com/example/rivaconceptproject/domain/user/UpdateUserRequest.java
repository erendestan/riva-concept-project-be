package com.example.rivaconceptproject.domain.user;

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
public class UpdateUserRequest {
    @NotNull
    private long id;
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String email;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String password;

    @NotNull
    private Role role;

    @NotNull
    private boolean isActive;
}
