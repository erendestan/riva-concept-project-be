package com.example.rivaconceptproject.business.reservationusecases;

import com.example.rivaconceptproject.domain.reservation.GetFilteredReservationsRequest;
import com.example.rivaconceptproject.domain.reservation.GetFilteredReservationsResponse;

public interface GetFilteredReservationsUseCase {
    GetFilteredReservationsResponse getFilteredReservations(GetFilteredReservationsRequest request);
}
