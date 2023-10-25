package com.example.rivaconceptproject.persistence.entity;

import com.example.rivaconceptproject.domain.enums.Event;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
public class ReservationEntity {
    private long reservationId;
    private long userId;
    private Event eventType;
    private LocalDateTime reservationCreatedDate;
    private LocalDateTime reservationDate;
    private LocalTime startTime;
    private LocalTime endTime;
}
