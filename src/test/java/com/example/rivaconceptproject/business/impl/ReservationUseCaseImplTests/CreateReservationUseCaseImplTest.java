package com.example.rivaconceptproject.business.impl.ReservationUseCaseImplTests;

import com.example.rivaconceptproject.business.exception.ReservationDateTakenException;
import com.example.rivaconceptproject.business.impl.ReservationUseCaseImpls.CreateReservationUseCaseImpl;
import com.example.rivaconceptproject.domain.Reservation.CreateReservationRequest;
import com.example.rivaconceptproject.domain.Reservation.CreateReservationResponse;
import com.example.rivaconceptproject.domain.Reservation.Reservation;
import com.example.rivaconceptproject.domain.User.User;
import com.example.rivaconceptproject.domain.enums.Event;
import com.example.rivaconceptproject.domain.enums.Role;
import com.example.rivaconceptproject.persistence.ReservationRepository;
import com.example.rivaconceptproject.persistence.entity.ReservationEntity;
import com.example.rivaconceptproject.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateReservationUseCaseImplTest {
    @Mock
    private ReservationRepository reservationRepositoryMock;

    @InjectMocks
    private CreateReservationUseCaseImpl createReservationUseCase;

    @Test
    void testCreateReservation() {
        User user = User.builder()
                .id(1L)
                .firstName("Jack")
                .lastName("Kral")
                .email("jackkral@gmail.com")
                .phoneNumber("555444111")
                .password("testpassword")
                .role(Role.Customer)
                .build();


        CreateReservationRequest request = CreateReservationRequest.builder()
                .user(user)
                .eventType(Event.Wedding)
                .reservationCreatedDate(LocalDateTime.of(2022, 11, 15, 18, 0))
                .reservationDate(LocalDateTime.of(2023, 11, 15, 18, 0))
                .startTime(LocalTime.of(18, 0))
                .endTime(LocalTime.of(23, 0))
                .build();



     //  when(userRepositoryMock.findById(request.getUser())).thenReturn(Optional.of(user));

        UserEntity userEntity = UserEntity.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
        ReservationEntity reservationEntity = ReservationEntity.builder()
                .reservationId(1L)
                .user(userEntity)
                .eventType(request.getEventType())
                .reservationCreatedDate(request.getReservationCreatedDate())
                .reservationDate(request.getReservationDate())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .build();

        when(reservationRepositoryMock.save(any(ReservationEntity.class)))
                .thenReturn(reservationEntity);

        CreateReservationResponse actualResult = createReservationUseCase.createReservation(request);



        Reservation reservation = Reservation.builder()
                .reservationId(1L)
                .user(user)
                .eventType(request.getEventType())
                .reservationCreatedDate(request.getReservationCreatedDate())
                .reservationDate(request.getReservationDate())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .build();

        CreateReservationResponse expectedResult = CreateReservationResponse.builder().reservationId(reservation.getReservationId()).userId(reservation.getUser().getId())
                .reservationDate(reservation.getReservationDate())
                .startTime(reservation.getStartTime())
                .endTime(reservation.getEndTime()).build();

        assertEquals(expectedResult, actualResult);

        verify(reservationRepositoryMock).save(ArgumentMatchers.any(ReservationEntity.class));
    }

    @Test
    void testCreateReservationDateTaken() {
        User user = User.builder()
                .id(1L)
                .firstName("Jack")
                .lastName("Kral")
                .email("jackkral@gmail.com")
                .phoneNumber("555444111")
                .password("testpassword")
                .role(Role.Customer)
                .build();
        // Create a sample request
        CreateReservationRequest request = CreateReservationRequest.builder()
                .reservationDate(LocalDate.of(2023, 11, 15).atTime(18,00,00))
                .user(user)
                .eventType(Event.Wedding)
                .reservationCreatedDate(LocalDateTime.now())
                .startTime(LocalTime.of(18, 0))
                .endTime(LocalTime.of(21, 0))
                .build();

        // Mock UserRepository behavior to return a UserEntity when findById is called
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        // Set other userEntity attributes
//        when(userRepositoryMock.findById(1L)).thenReturn(Optional.of(userEntity));

        // Mock ReservationRepository behavior to return true when existsByReservationDate is called
        when(reservationRepositoryMock.existsByReservationDate(request.getReservationDate())).thenReturn(true);

        // This should throw a ReservationDateTakenException
        assertThrows(ReservationDateTakenException.class, () -> createReservationUseCase.createReservation(request));
    }

}
