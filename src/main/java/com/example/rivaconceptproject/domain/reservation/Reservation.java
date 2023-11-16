package com.example.rivaconceptproject.domain.reservation;

import com.example.rivaconceptproject.domain.user.User;
import com.example.rivaconceptproject.domain.enums.Event;
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
public class Reservation {
    private long reservationId;
    private User user;
//    private long userId; //Change it to your User object
    private Event eventType;
    private LocalDateTime reservationCreatedDate;
    private LocalDateTime reservationDate;
    private LocalTime startTime;
    private LocalTime endTime;


}
