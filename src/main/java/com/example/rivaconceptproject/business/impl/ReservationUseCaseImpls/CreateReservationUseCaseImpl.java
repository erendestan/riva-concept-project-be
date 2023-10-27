package com.example.rivaconceptproject.business.impl.ReservationUseCaseImpls;

import com.example.rivaconceptproject.business.ReservationUseCases.CreateReservationUseCase;
import com.example.rivaconceptproject.business.exception.ReservationDateTakenException;
import com.example.rivaconceptproject.domain.Reservation.CreateReservationRequest;
import com.example.rivaconceptproject.domain.Reservation.CreateReservationResponse;
import com.example.rivaconceptproject.persistence.ReservationRepository;
import com.example.rivaconceptproject.persistence.entity.ReservationEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateReservationUseCaseImpl implements CreateReservationUseCase {
    private final ReservationRepository reservationRepository;
    @Override
    public CreateReservationResponse createReservation(CreateReservationRequest request) {
        if (reservationRepository.existsByDateTime(request.getReservationDate())){
            throw new ReservationDateTakenException();
        }

        ReservationEntity reservation = saveNewReservation(request);

        return CreateReservationResponse.builder().
                reservationId(reservation.getReservationId())
                .userId(reservation.getUserId())
                .reservationDate(reservation.getReservationDate())
                .startTime(reservation.getStartTime())
                .endTime(reservation.getEndTime())
                .build();
    }

    private ReservationEntity saveNewReservation(CreateReservationRequest request){
        ReservationEntity newReservation = ReservationEntity.builder()
                .userId(request.getUserId())
                .eventType(request.getEventType())
                .reservationCreatedDate(request.getReservationCreatedDate())
                .reservationDate(request.getReservationDate())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .build();

        return reservationRepository.save(newReservation);
    }
}
