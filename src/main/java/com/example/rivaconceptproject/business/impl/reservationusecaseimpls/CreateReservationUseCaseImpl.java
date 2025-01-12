package com.example.rivaconceptproject.business.impl.reservationusecaseimpls;

import com.example.rivaconceptproject.business.exception.InvalidUserException;
import com.example.rivaconceptproject.business.exception.ReservationDateTakenException;
import com.example.rivaconceptproject.business.reservationusecases.CreateReservationUseCase;
import com.example.rivaconceptproject.domain.reservation.CreateReservationRequest;
import com.example.rivaconceptproject.domain.reservation.CreateReservationResponse;
import com.example.rivaconceptproject.persistence.ReservationRepository;
import com.example.rivaconceptproject.persistence.UserRepository;
import com.example.rivaconceptproject.persistence.entity.ReservationEntity;
import com.example.rivaconceptproject.persistence.entity.UserEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateReservationUseCaseImpl implements CreateReservationUseCase {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public CreateReservationResponse createReservation(CreateReservationRequest request) {
        if (reservationRepository.existsByReservationDate(request.getReservationDate())){
            throw new ReservationDateTakenException();
        }

        ReservationEntity reservation = saveNewReservation(request);

        return CreateReservationResponse.builder().
                reservationId(reservation.getReservationId())
                .userId(reservation.getUser().getId())
                .reservationDate(reservation.getReservationDate())
                .startTime(reservation.getStartTime())
                .endTime(reservation.getEndTime())
                .build();
    }

    private ReservationEntity saveNewReservation(CreateReservationRequest request){
        UserEntity user = userRepository.findById(request.getUserId()).orElseThrow(() -> new InvalidUserException("User not found with ID: " + request.getUserId()));
        ReservationEntity newReservation = ReservationEntity.builder()
//                .user(getUserEntity(request.getUser()))
                .user(user)
                .eventType(request.getEventType())
                .reservationCreatedDate(request.getReservationCreatedDate())
                .reservationDate(request.getReservationDate())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .build();

        user.getReservation().add(newReservation);

        return reservationRepository.save(newReservation);
    }

}
