package com.example.rivaconceptproject.business.impl.ReservationUseCaseImplTests;

import com.example.rivaconceptproject.business.exception.ReservationRetrivalException;
import com.example.rivaconceptproject.business.impl.ReservationUseCaseImpls.GetReservationsUseCaseImpl;
import com.example.rivaconceptproject.business.impl.ReservationUseCaseImpls.ReservationConverter;
import com.example.rivaconceptproject.domain.Reservation.CreateReservationRequest;
import com.example.rivaconceptproject.domain.Reservation.GetAllReservationsResponse;
import com.example.rivaconceptproject.domain.Reservation.Reservation;
import com.example.rivaconceptproject.domain.User.User;
import com.example.rivaconceptproject.domain.enums.Event;
import com.example.rivaconceptproject.domain.enums.Role;
import com.example.rivaconceptproject.persistence.ReservationRepository;
import com.example.rivaconceptproject.persistence.entity.ReservationEntity;
import com.example.rivaconceptproject.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetReservationsUseCaseImplTest {
    @Mock
    private ReservationRepository reservationRepositoryMock;
    @InjectMocks
    private GetReservationsUseCaseImpl getReservationsUseCase;

    @Test
    void getReservations_shouldGetAllTheReservation_ShouldReturnTheExpectedSize(){
        User user = User.builder()
                .id(1L)
                .firstName("Jack")
                .lastName("Kral")
                .email("jackkral@gmail.com")
                .phoneNumber("555444111")
                .password("testpassword")
                .role(Role.CUSTOMER)
                .build();

        CreateReservationRequest request1 = CreateReservationRequest.builder()
                .user(user)
                .eventType(Event.WEDDING)
                .reservationCreatedDate(LocalDateTime.of(2022, 11, 15, 18, 0))
                .reservationDate(LocalDateTime.of(2023, 11, 15, 18, 0))
                .startTime(LocalTime.of(18, 0))
                .endTime(LocalTime.of(23, 0))
                .build();

        CreateReservationRequest request2 = CreateReservationRequest.builder()
                .user(user)
                .eventType(Event.WEDDING)
                .reservationCreatedDate(LocalDateTime.of(2022, 11, 20, 15, 0))
                .reservationDate(LocalDateTime.of(2023, 12, 1, 15, 0))
                .startTime(LocalTime.of(15, 0))
                .endTime(LocalTime.of(17, 0))
                .build();

        UserEntity userEntity = UserEntity.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .password(user.getPassword())
                .role(user.getRole())
                .build();

        ReservationEntity reservationEntity1 = ReservationEntity.builder()
                .reservationId(1L)
                .user(userEntity)
                .eventType(request1.getEventType())
                .reservationCreatedDate(request1.getReservationCreatedDate())
                .reservationDate(request1.getReservationDate())
                .startTime(request1.getStartTime())
                .endTime(request1.getEndTime())
                .build();

        ReservationEntity reservationEntity2 = ReservationEntity.builder()
                .reservationId(2L)
                .user(userEntity)
                .eventType(request2.getEventType())
                .reservationCreatedDate(request2.getReservationCreatedDate())
                .reservationDate(request2.getReservationDate())
                .startTime(request2.getStartTime())
                .endTime(request2.getEndTime())
                .build();

        when(reservationRepositoryMock.findAll()).thenReturn(Arrays.asList(reservationEntity1, reservationEntity2));

        // When
        GetAllReservationsResponse result = getReservationsUseCase.getReservations();

        // Then
        List<Reservation> expectedReservations = Arrays.asList(
                ReservationConverter.convert(reservationEntity1),
                ReservationConverter.convert(reservationEntity2)
        );

        assertEquals(expectedReservations.size(), result.getReservations().size());

        // Additional verification
        verify(reservationRepositoryMock).findAll();
        // You can add more specific verifications based on your actual implementation
    }

    @Test
    void getReservations_repositoryException(){
        when(reservationRepositoryMock.findAll()).thenThrow(new ReservationRetrivalException("Failed to retrieve reservations"));

        assertThrows(ReservationRetrivalException.class, () -> getReservationsUseCase.getReservations());

        verify(reservationRepositoryMock).findAll();
    }
}