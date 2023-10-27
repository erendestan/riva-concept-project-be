package com.example.rivaconceptproject.business.impl.ReservationUseCaseImpls;

import com.example.rivaconceptproject.business.ReservationUseCases.DeleteReservationUseCase;
import com.example.rivaconceptproject.persistence.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteReservationUseCaseImpl implements DeleteReservationUseCase {
    private final ReservationRepository reservationRepository;
    @Override
    public void deleteReservation(long reservationId) {
        this.reservationRepository.deleteByReservationId(reservationId);
    }
}
