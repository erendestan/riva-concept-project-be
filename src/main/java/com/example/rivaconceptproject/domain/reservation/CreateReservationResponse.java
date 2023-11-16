package com.example.rivaconceptproject.domain.reservation;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
public class CreateReservationResponse {
    private long reservationId;
    private long userId;
    private LocalDateTime reservationDate;
    private LocalTime startTime;
    private LocalTime endTime;
}
