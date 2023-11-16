package com.example.rivaconceptproject.business.impl.reservationusecaseimpls;

import com.example.rivaconceptproject.business.reservationusecases.DeleteReservationUseCase;
import com.example.rivaconceptproject.business.exception.ReservationNotFoundException;
import com.example.rivaconceptproject.persistence.ReservationRepository;
import com.example.rivaconceptproject.persistence.entity.ReservationEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DeleteReservationUseCaseImpl implements DeleteReservationUseCase {
    private final ReservationRepository reservationRepository;
    @Transactional
    @Override
    public void deleteReservation(long reservationId) {
        Optional<ReservationEntity> reservationOptional = reservationRepository.findByReservationId(reservationId);

        if(reservationOptional.isPresent()){
            reservationRepository.deleteById(reservationId);
        }else{
            throw new ReservationNotFoundException("Reservation with ID " + reservationId + " not found.");
        }

    }
}
