package com.example.rivaconceptproject.business.impl.reservationusecaseimpls;

import com.example.rivaconceptproject.business.reservationusecases.GetReservationUseCase;
import com.example.rivaconceptproject.business.exception.ReservationNotFoundException;
import com.example.rivaconceptproject.domain.reservation.Reservation;
import com.example.rivaconceptproject.persistence.ReservationRepository;
import com.example.rivaconceptproject.persistence.entity.ReservationEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GetReservationUseCaseImpl implements GetReservationUseCase {
    private ReservationRepository reservationRepository;
    @Transactional
    @Override
    public Optional<Reservation> getReservation(long reservationId) {
        Optional<Reservation> reservationOptional = reservationRepository.findByReservationId(reservationId).map(ReservationConverter::convert);
        if(reservationOptional.isEmpty()){
            throw new ReservationNotFoundException("Reservation with ID" + reservationId + "not found");
        }
        return reservationOptional;
    }
    @Transactional
    @Override
    public Optional<Reservation> getReservationByUserId(long userId) {
        return reservationRepository.findByReservationId(userId).map(ReservationConverter::convert);
    }

    @Transactional
    @Override
    public Optional<ReservationEntity> findByReservationDate(LocalDateTime reservationDate){
        return reservationRepository.findByReservationDate(reservationDate);
    }

}
