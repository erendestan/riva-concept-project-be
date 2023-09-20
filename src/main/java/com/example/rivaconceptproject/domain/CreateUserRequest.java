package com.example.rivaconceptproject.domain;


import com.example.rivaconceptproject.domain.enums.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
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
