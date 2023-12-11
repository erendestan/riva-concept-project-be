package com.example.rivaconceptproject.domain.reservation;

import com.example.rivaconceptproject.domain.enums.Event;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateReservationRequest {

    @NotNull
    private Long userId;
//    private User user;
    @NotNull
    private Event eventType;

    @NotNull
    private LocalDateTime reservationCreatedDate;

    @NotNull
    private LocalDateTime reservationDate;
    @NotNull
    private LocalTime startTime;
    @NotNull
    private LocalTime endTime;
}
