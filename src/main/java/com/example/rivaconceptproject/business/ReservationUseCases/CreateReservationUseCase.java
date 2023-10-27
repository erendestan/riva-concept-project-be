package com.example.rivaconceptproject.business.ReservationUseCases;

import com.example.rivaconceptproject.domain.Reservation.CreateReservationRequest;
import com.example.rivaconceptproject.domain.Reservation.CreateReservationResponse;

public interface CreateReservationUseCase {
    CreateReservationResponse createReservation(CreateReservationRequest request);
}
