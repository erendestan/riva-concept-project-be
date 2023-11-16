package com.example.rivaconceptproject.business.impl.ReservationUseCaseImplTests;

import com.example.rivaconceptproject.business.exception.ReservationNotFoundException;
import com.example.rivaconceptproject.business.impl.reservationusecaseimpls.DeleteReservationUseCaseImpl;
import com.example.rivaconceptproject.domain.reservation.CreateReservationRequest;
import com.example.rivaconceptproject.domain.user.User;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteReservationUseCaseImplTest {
    @Mock
    private ReservationRepository reservationRepositoryMock;
    @InjectMocks
    private DeleteReservationUseCaseImpl deleteReservationUseCase;

    @Test
    void deleteReservation_deleteByTheReservationId(){
        User user = User.builder()
                .id(1L)
                .firstName("Jack")
                .lastName("Kral")
                .email("jackkral@gmail.com")
                .phoneNumber("555444111")
                .password("testpassword")
                .role(Role.CUSTOMER)
                .build();


        CreateReservationRequest request = CreateReservationRequest.builder()
                .user(user)
                .eventType(Event.WEDDING)
                .reservationCreatedDate(LocalDateTime.of(2022, 11, 15, 18, 0))
                .reservationDate(LocalDateTime.of(2023, 11, 15, 18, 0))
                .startTime(LocalTime.of(18, 0))
                .endTime(LocalTime.of(23, 0))
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
        ReservationEntity reservationEntity = ReservationEntity.builder()
                .reservationId(1L)
                .user(userEntity)
                .eventType(request.getEventType())
                .reservationCreatedDate(request.getReservationCreatedDate())
                .reservationDate(request.getReservationDate())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .build();

        when(reservationRepositoryMock.findByReservationId(1L)).thenReturn(Optional.of(reservationEntity));

        deleteReservationUseCase.deleteReservation(1L);

        verify(reservationRepositoryMock).deleteById(1L);

        when(reservationRepositoryMock.findByReservationId(1L)).thenReturn(Optional.empty());
        assertFalse(reservationRepositoryMock.findByReservationId(1L).isPresent());
    }

    @Test
    void deleteReservation_reservationNotExist_ShouldThrowException(){
        when(reservationRepositoryMock.findByReservationId(2L)).thenReturn(Optional.empty());

        ReservationNotFoundException exception = assertThrows(ReservationNotFoundException.class, () -> deleteReservationUseCase.deleteReservation(2L));

        assertTrue(exception.getMessage().contains("Reservation with ID 2 not found."));

        verify(reservationRepositoryMock, never()).deleteById(2L);
    }

}
