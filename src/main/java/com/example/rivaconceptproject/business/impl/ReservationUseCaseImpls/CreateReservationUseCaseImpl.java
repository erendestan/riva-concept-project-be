package com.example.rivaconceptproject.business.impl.ReservationUseCaseImpls;

import com.example.rivaconceptproject.business.ReservationUseCases.CreateReservationUseCase;
import com.example.rivaconceptproject.domain.Reservation.CreateReservationRequest;
import com.example.rivaconceptproject.domain.Reservation.CreateReservationResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateReservationUseCaseImpl implements CreateReservationUseCase {
    @Override
    public CreateReservationResponse createReservation(CreateReservationRequest request) {
        return null;
    }
}
