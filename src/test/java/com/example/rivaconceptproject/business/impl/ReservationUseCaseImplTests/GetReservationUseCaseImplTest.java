package com.example.rivaconceptproject.business.impl.ReservationUseCaseImplTests;

import com.example.rivaconceptproject.business.exception.ReservationNotFoundException;
import com.example.rivaconceptproject.business.impl.ReservationUseCaseImpls.GetReservationUseCaseImpl;
import com.example.rivaconceptproject.domain.reservation.Reservation;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetReservationUseCaseImplTest {
    @Mock
    private ReservationRepository reservationRepositoryMock;
    @InjectMocks
    private GetReservationUseCaseImpl getReservationUseCase;

    @Test
    void shouldRetrieveReservationById(){
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .firstName("Jack")
                .lastName("Kral")
                .email("jackkral@gmail.com")
                .phoneNumber("555444111")
                .password("testpassword")
                .role(Role.CUSTOMER)
                .build();

        ReservationEntity reservationEntity = ReservationEntity.builder()
                .user(userEntity)
                .eventType(Event.WEDDING)
                .reservationCreatedDate(LocalDateTime.of(2022, 11, 15, 18, 0))
                .reservationDate(LocalDateTime.of(2023, 11, 15, 18, 0))
                .startTime(LocalTime.of(18, 0))
                .endTime(LocalTime.of(23, 0))
                .build();



        long reservationId = 1L;
        when(reservationRepositoryMock.findByReservationId(reservationId)).thenReturn(Optional.of(reservationEntity));
        Optional<Reservation> reservationOptional = getReservationUseCase.getReservation(reservationId);

        User user = User.builder()
                .id(1L)
                .firstName("Jack")
                .lastName("Kral")
                .email("jackkral@gmail.com")
                .phoneNumber("555444111")
                .password("testpassword")
                .role(Role.CUSTOMER)
                .build();
        Reservation reservation = Reservation.builder()
                .user(user)
                .eventType(Event.WEDDING)
                .reservationCreatedDate(LocalDateTime.of(2022, 11, 15, 18, 0))
                .reservationDate(LocalDateTime.of(2023, 11, 15, 18, 0))
                .startTime(LocalTime.of(18, 0))
                .endTime(LocalTime.of(23, 0))
                .build();
       // assertTrue(reservationOptional.isPresent());
        Reservation reservation1 = reservationOptional.get();
        assertEquals(reservation, reservation1);
    }

    @Test
    void getReservation_shouldThrowReservationNotFoundException(){
        Long reservationId = 2L;

        when(reservationRepositoryMock.findByReservationId(reservationId)).thenReturn(Optional.empty());

        assertThrows(ReservationNotFoundException.class, () -> getReservationUseCase.getReservation(reservationId));

        verify(reservationRepositoryMock).findByReservationId(reservationId);
    }

}
