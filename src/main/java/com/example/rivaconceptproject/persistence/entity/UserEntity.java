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

@Entity
@Table(name = "user")

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

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
    private long phoneNumber;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "role")
    @Enumerated(EnumType.ORDINAL)
    private Role role;

//    @OneToMany(mappedBy = "user")
//    @NotNull
//    @ManyToOne
//    @JoinColumn(name = "")
//    private List<ReservationEntity> reservations;
//    private Reservation reservation;
}
