package com.example.rivaconceptproject.domain.reservation;


import com.example.rivaconceptproject.domain.enums.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetFilteredReservationsRequest {
    private Event eventType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
