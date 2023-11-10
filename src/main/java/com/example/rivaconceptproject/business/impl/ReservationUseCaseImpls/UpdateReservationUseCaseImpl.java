package com.example.rivaconceptproject.business.impl.ReservationUseCaseImpls;

import com.example.rivaconceptproject.business.ReservationUseCases.UpdateReservationUseCase;
import com.example.rivaconceptproject.business.exception.InvalidReservationException;
import com.example.rivaconceptproject.domain.Reservation.UpdateReservationRequest;
import com.example.rivaconceptproject.persistence.ReservationRepository;
import com.example.rivaconceptproject.persistence.UserRepository;
import com.example.rivaconceptproject.persistence.entity.ReservationEntity;
import com.example.rivaconceptproject.persistence.entity.UserEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateReservationUseCaseImpl implements UpdateReservationUseCase {
   private final ReservationRepository reservationRepository;
   private final UserRepository userRepository;
    @Transactional
   @Override
    public void updateReservation(UpdateReservationRequest request) {
        Optional<ReservationEntity> reservationOptional = reservationRepository.findByReservationId(request.getReservationId());
        if (reservationOptional.isEmpty()){
            throw new InvalidReservationException("RESERVATION_ID_INVALID");
        }

        ReservationEntity reservation = reservationOptional.get();
        updateFields(request, reservation);

    }

    private void updateFields(UpdateReservationRequest request, ReservationEntity reservation){
        Optional<UserEntity> userEntity = userRepository.findById(request.getUserId());

        if (userEntity.isPresent()) {
            reservation.setUser(userEntity.get());
            reservation.setEventType(request.getEventType());
            reservation.setReservationCreatedDate(request.getReservationCreatedDate());
            reservation.setReservationDate(request.getReservationDate());
            reservation.setStartTime(request.getStartTime());
            reservation.setEndTime(request.getEndTime());
            reservationRepository.save(reservation);
        } else {
            throw new InvalidReservationException("RESERVATION_IS_INVALID");
        }
    }
}
