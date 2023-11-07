package com.example.rivaconceptproject.business.impl.ReservationUseCaseImpls;

import com.example.rivaconceptproject.business.ReservationUseCases.GetReservationUseCase;
import com.example.rivaconceptproject.domain.Reservation.Reservation;
import com.example.rivaconceptproject.persistence.ReservationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetReservationUseCaseImpl implements GetReservationUseCase {
    private ReservationRepository reservationRepository;
    @Transactional
    @Override
    public Optional<Reservation> getReservation(long reservationId) {
        return reservationRepository.findReservationById(reservationId).map(ReservationConverter::convert);
    }
    @Transactional
    @Override
    public Optional<Reservation> getReservationByUserId(long userId) {
        return reservationRepository.findReservationByUserId(userId).map(ReservationConverter::convert);
    }
}
