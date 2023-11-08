package com.example.rivaconceptproject.business.impl.ReservationUseCaseImpls;

import com.example.rivaconceptproject.business.ReservationUseCases.DeleteReservationUseCase;
import com.example.rivaconceptproject.persistence.ReservationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteReservationUseCaseImpl implements DeleteReservationUseCase {
    private final ReservationRepository reservationRepository;
    @Transactional
    @Override
    public void deleteReservation(long reservationId) {
        this.reservationRepository.deleteById(reservationId);
    }
}
