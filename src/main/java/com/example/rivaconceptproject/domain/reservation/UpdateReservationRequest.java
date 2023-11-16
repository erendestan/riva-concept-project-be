package com.example.rivaconceptproject.domain.reservation;

import com.example.rivaconceptproject.domain.enums.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateReservationRequest {
    @NotNull
    private long reservationId;
    @NotNull
    private long userId;
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
