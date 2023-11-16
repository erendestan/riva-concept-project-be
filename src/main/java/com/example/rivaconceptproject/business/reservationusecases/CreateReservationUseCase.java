package com.example.rivaconceptproject.business.reservationusecases;

import com.example.rivaconceptproject.domain.reservation.CreateReservationRequest;
import com.example.rivaconceptproject.domain.reservation.CreateReservationResponse;

public interface CreateReservationUseCase {
    CreateReservationResponse createReservation(CreateReservationRequest request);
}
