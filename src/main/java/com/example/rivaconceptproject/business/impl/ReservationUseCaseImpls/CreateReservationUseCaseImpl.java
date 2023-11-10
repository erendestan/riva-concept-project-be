package com.example.rivaconceptproject.business.impl.ReservationUseCaseImpls;

import com.example.rivaconceptproject.business.ReservationUseCases.CreateReservationUseCase;
import com.example.rivaconceptproject.business.exception.ReservationDateTakenException;
import com.example.rivaconceptproject.domain.Reservation.CreateReservationRequest;
import com.example.rivaconceptproject.domain.Reservation.CreateReservationResponse;
import com.example.rivaconceptproject.domain.User.User;
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
//                .userId(reservation.getUserId())
                .userId(reservation.getUser().getId())
                .reservationDate(reservation.getReservationDate())
                .startTime(reservation.getStartTime())
                .endTime(reservation.getEndTime())
                .build();
    }

    private ReservationEntity saveNewReservation(CreateReservationRequest request){
      //  Optional<UserEntity> userEntity = userRepository.findById(request.getUser());
        ReservationEntity newReservation = ReservationEntity.builder()
//                .userId(request.getUserId())
                .user(getUserEntity(request.getUser()))
                .eventType(request.getEventType())
                .reservationCreatedDate(request.getReservationCreatedDate())
                .reservationDate(request.getReservationDate())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .build();

        return reservationRepository.save(newReservation);
    }

    private  UserEntity getUserEntity( User user){
        return UserEntity.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .password(user.getPassword())
                .role(user.getRole())
//                .reservation((List<Reservation>) user.getReservation())
                .build();
    }

}
