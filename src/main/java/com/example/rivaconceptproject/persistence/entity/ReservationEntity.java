package com.example.rivaconceptproject.persistence.entity;

import com.example.rivaconceptproject.domain.enums.Event;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "reservation")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_ID")
    private long reservationId;

//    private long userId;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_ID_FK")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private UserEntity user;

    @NotNull
    @Column(name = "event")
    @Enumerated(EnumType.ORDINAL)
    private Event eventType;

    @NotNull
    @Column(name = "reservation_created_date")
    private LocalDateTime reservationCreatedDate;

    @NotNull
    @Column(name = "reservation_date")
    private LocalDateTime reservationDate;

    @NotNull
    @Column(name = "start_time")
    private LocalTime startTime;

    @NotNull
    @Column(name = "end_time")
    private LocalTime endTime;
}
