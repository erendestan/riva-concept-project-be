package com.example.rivaconceptproject.persistence.entity;

import com.example.rivaconceptproject.domain.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_ID")
    private Long id;

    @NotBlank
    @Length(min = 2, max = 20)
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Length(min = 2, max = 30)
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @Length(min = 2, max = 25)
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotBlank
    @Length(max = 100)
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "role")
    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @NotNull
    @Column(name = "active")
    private boolean isActive;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private final List<ReservationEntity> reservation = new ArrayList<>();
}
