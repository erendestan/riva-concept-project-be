package com.example.rivaconceptproject.business.impl.ReservationUseCaseImplTests;

import com.example.rivaconceptproject.business.exception.ReservationRetrivalException;
import com.example.rivaconceptproject.business.impl.reservationusecaseimpls.GetFilteredReservationsUseCaseImpl;
import com.example.rivaconceptproject.business.impl.userusecaseimpls.UserConverter;
import com.example.rivaconceptproject.domain.enums.Event;
import com.example.rivaconceptproject.domain.enums.Role;
import com.example.rivaconceptproject.domain.reservation.GetFilteredReservationsRequest;
import com.example.rivaconceptproject.domain.reservation.GetFilteredReservationsResponse;
import com.example.rivaconceptproject.domain.user.User;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetFilteredReservationsUseCaseImplTest {

    @Mock
    private ReservationRepository reservationRepositoryMock;

    @InjectMocks
    private GetFilteredReservationsUseCaseImpl getFilteredReservationsUseCase;

    @Test
    void getFilteredReservations_noFilterCriteria_shouldReturnAllReservations() {
        // Create test data
        User user = User.builder()
                .id(1L)
                .firstName("Jack")
                .lastName("Kral")
                .email("jackkral@gmail.com")
                .phoneNumber("555444111")
                .password("testpassword")
                .role(Role.CUSTOMER)
                .build();

        UserEntity userEntity = UserConverter.convertUserEntity(user);

        ReservationEntity reservationEntity1 = ReservationEntity.builder()
                .reservationId(1L)
                .user(userEntity)
                .eventType(Event.WEDDING)
                .reservationCreatedDate(LocalDateTime.of(2022, 11, 15, 18, 0))
                .reservationDate(LocalDateTime.of(2023, 11, 15, 18, 0))
                .startTime(LocalTime.of(18, 0))
                .endTime(LocalTime.of(23, 0))
                .build();

        ReservationEntity reservationEntity2 = ReservationEntity.builder()
                .reservationId(2L)
                .user(userEntity)
                .eventType(Event.WEDDING)
                .reservationCreatedDate(LocalDateTime.of(2022, 11, 20, 15, 0))
                .reservationDate(LocalDateTime.of(2023, 12, 1, 15, 0))
                .startTime(LocalTime.of(15, 0))
                .endTime(LocalTime.of(17, 0))
                .build();

        List<ReservationEntity> reservationEntities = Arrays.asList(reservationEntity1, reservationEntity2);

        // Mock repository behavior
        when(reservationRepositoryMock.findAll()).thenReturn(reservationEntities);

        // Execute the use case
        GetFilteredReservationsResponse result = getFilteredReservationsUseCase.getFilteredReservations(
                GetFilteredReservationsRequest.builder().build()
        );

        // Verify the result
        assertEquals(reservationEntities.size(), result.getFilteredReservations().size());

        // Additional verification
        verify(reservationRepositoryMock).findAll();
    }

    @Test
    void getFilteredReservations_withFilterCriteria_shouldReturnFilteredReservations() {
        // Create test data
        User user = User.builder()
                .id(1L)
                .firstName("Jack")
                .lastName("Kral")
                .email("jackkral@gmail.com")
                .phoneNumber("555444111")
                .password("testpassword")
                .role(Role.CUSTOMER)
                .build();

        UserEntity userEntity = UserConverter.convertUserEntity(user);

        ReservationEntity reservationEntity1 = ReservationEntity.builder()
                .reservationId(1L)
                .user(userEntity)
                .eventType(Event.WEDDING)
                .reservationCreatedDate(LocalDateTime.of(2022, 11, 15, 18, 0))
                .reservationDate(LocalDateTime.of(2023, 11, 15, 18, 0))
                .startTime(LocalTime.of(18, 0))
                .endTime(LocalTime.of(23, 0))
                .build();

        ReservationEntity reservationEntity2 = ReservationEntity.builder()
                .reservationId(2L)
                .user(userEntity)
                .eventType(Event.WEDDING)
                .reservationCreatedDate(LocalDateTime.of(2022, 11, 20, 15, 0))
                .reservationDate(LocalDateTime.of(2023, 12, 1, 15, 0))
                .startTime(LocalTime.of(15, 0))
                .endTime(LocalTime.of(17, 0))
                .build();

        List<ReservationEntity> reservationEntities = Arrays.asList(reservationEntity1, reservationEntity2);

        // Mock repository behavior
        when(reservationRepositoryMock.findFilteredReservations(
                Event.WEDDING,
                LocalDateTime.of(2022, 11, 15, 18, 0),
                LocalDateTime.of(2023, 12, 1, 15, 0)
        )).thenReturn(Optional.of(reservationEntities));

        // Execute the use case
        GetFilteredReservationsResponse result = getFilteredReservationsUseCase.getFilteredReservations(
                GetFilteredReservationsRequest.builder()
                        .eventType(Event.WEDDING)
                        .startDate(LocalDateTime.of(2022, 11, 15, 18, 0))
                        .endDate(LocalDateTime.of(2023, 12, 1, 15, 0))
                        .build()
        );

        // Verify the result
        assertEquals(reservationEntities.size(), result.getFilteredReservations().size());

        // Additional verification
        verify(reservationRepositoryMock).findFilteredReservations(
                Event.WEDDING,
                LocalDateTime.of(2022, 11, 15, 18, 0),
                LocalDateTime.of(2023, 12, 1, 15, 0)
        );
    }

    @Test
    void getFilteredReservations_repositoryException() {
        // Mock repository to throw an exception
        when(reservationRepositoryMock.findFilteredReservations(
                Event.WEDDING,
                LocalDateTime.of(2022, 11, 15, 18, 0),
                LocalDateTime.of(2023, 12, 1, 15, 0)
        )).thenThrow(new ReservationRetrivalException("Failed to retrieve filtered reservations"));

        // Execute the use case and verify the exception
        assertThrows(ReservationRetrivalException.class, () ->
                getFilteredReservationsUseCase.getFilteredReservations(
                        GetFilteredReservationsRequest.builder()
                                .eventType(Event.WEDDING)
                                .startDate(LocalDateTime.of(2022, 11, 15, 18, 0))
                                .endDate(LocalDateTime.of(2023, 12, 1, 15, 0))
                                .build()
                ));

        // Additional verification
        verify(reservationRepositoryMock).findFilteredReservations(
                Event.WEDDING,
                LocalDateTime.of(2022, 11, 15, 18, 0),
                LocalDateTime.of(2023, 12, 1, 15, 0)
        );
    }


}