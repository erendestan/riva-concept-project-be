package com.example.rivaconceptproject.domain.reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetFilteredReservationsResponse {
    private List<Reservation> filteredReservations;
}
