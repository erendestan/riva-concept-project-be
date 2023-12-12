package com.example.rivaconceptproject.business.impl.ReservationUseCaseImplTests;

import com.example.rivaconceptproject.business.exception.ReservationDateTakenException;
import com.example.rivaconceptproject.business.impl.reservationusecaseimpls.CreateReservationUseCaseImpl;
import com.example.rivaconceptproject.domain.enums.Event;
import com.example.rivaconceptproject.domain.enums.Role;
import com.example.rivaconceptproject.domain.reservation.CreateReservationRequest;
import com.example.rivaconceptproject.domain.reservation.CreateReservationResponse;
import com.example.rivaconceptproject.domain.reservation.Reservation;
import com.example.rivaconceptproject.domain.user.User;
import com.example.rivaconceptproject.persistence.ReservationRepository;
import com.example.rivaconceptproject.persistence.UserRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateReservationUseCaseImplTest {
    @Mock
    private ReservationRepository reservationRepositoryMock;

    @Mock
    private UserRepository userRepositoryMock;

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
                .role(Role.CUSTOMER)
                .build();

        when(userRepositoryMock.findById(1L)).thenReturn(Optional.of(UserEntity.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .password(user.getPassword())
                .role(user.getRole())
                .build()));

        CreateReservationRequest request = CreateReservationRequest.builder()
                .userId(1L)
                .eventType(Event.WEDDING)
                .reservationCreatedDate(LocalDateTime.of(2022, 11, 15, 18, 0))
                .reservationDate(LocalDateTime.of(2023, 11, 15, 18, 0))
                .startTime(LocalTime.of(18, 0))
                .endTime(LocalTime.of(23, 0))
                .build();

        ReservationEntity reservationEntity = ReservationEntity.builder()
                .reservationId(1L)
                .user(UserEntity.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .phoneNumber(user.getPhoneNumber())
                        .password(user.getPassword())
                        .role(user.getRole())
                        .build())
                .eventType(request.getEventType())
                .reservationCreatedDate(request.getReservationCreatedDate())
                .reservationDate(request.getReservationDate())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .build();

        when(reservationRepositoryMock.save(ArgumentMatchers.any(ReservationEntity.class)))
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

        CreateReservationResponse expectedResult = CreateReservationResponse.builder()
                .reservationId(reservation.getReservationId())
                .userId(reservation.getUser().getId())
                .reservationDate(reservation.getReservationDate())
                .startTime(reservation.getStartTime())
                .endTime(reservation.getEndTime())
                .build();

        assertEquals(expectedResult, actualResult);

        verify(reservationRepositoryMock).save(ArgumentMatchers.any(ReservationEntity.class));
    }

    @Test
    void testCreateReservationDateTaken() {
        CreateReservationRequest request = CreateReservationRequest.builder()
                .reservationDate(LocalDate.of(2023, 11, 15).atTime(18, 0, 0))
                .userId(1L)
                .eventType(Event.WEDDING)
                .reservationCreatedDate(LocalDateTime.now())
                .startTime(LocalTime.of(18, 0))
                .endTime(LocalTime.of(21, 0))
                .build();

        when(reservationRepositoryMock.existsByReservationDate(request.getReservationDate())).thenReturn(true);

        assertThrows(ReservationDateTakenException.class, () -> createReservationUseCase.createReservation(request));
    }
}
