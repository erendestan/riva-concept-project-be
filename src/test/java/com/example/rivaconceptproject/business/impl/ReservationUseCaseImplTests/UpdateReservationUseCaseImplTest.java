package com.example.rivaconceptproject.business.impl.ReservationUseCaseImplTests;

import com.example.rivaconceptproject.business.exception.InvalidReservationException;
import com.example.rivaconceptproject.business.impl.reservationusecaseimpls.UpdateReservationUseCaseImpl;
import com.example.rivaconceptproject.domain.enums.Event;
import com.example.rivaconceptproject.domain.enums.Role;
import com.example.rivaconceptproject.domain.reservation.UpdateReservationRequest;
import com.example.rivaconceptproject.persistence.ReservationRepository;
import com.example.rivaconceptproject.persistence.UserRepository;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateReservationUseCaseImplTest {
    @Mock
    private ReservationRepository reservationRepositoryMock;
    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private UpdateReservationUseCaseImpl updateReservationUseCase;

    @Test
    void testUpdateReservation() {
        // Arrange
        UpdateReservationRequest request = createSampleUpdateReservationRequest();
        ReservationEntity existingReservation = createSampleReservationEntity();

        when(reservationRepositoryMock.findByReservationId(request.getReservationId())).thenReturn(Optional.of(existingReservation));
        when(userRepositoryMock.findById(request.getUserId())).thenReturn(Optional.of(createSampleUserEntity()));

        // Act
        updateReservationUseCase.updateReservation(request);

        // Assert
        verify(reservationRepositoryMock).findByReservationId(request.getReservationId());
        verify(userRepositoryMock).findById(request.getUserId());
        verify(reservationRepositoryMock).save(existingReservation); // Ensure that the save method is called
    }

    @Test
    void testUpdateReservationInvalidReservationId() {
        // Arrange
        UpdateReservationRequest request = createSampleUpdateReservationRequest();
        when(reservationRepositoryMock.findByReservationId(request.getReservationId())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(InvalidReservationException.class, () -> updateReservationUseCase.updateReservation(request));

        // Verify that methods were called with the correct arguments
        verify(reservationRepositoryMock).findByReservationId(request.getReservationId());
        verifyNoMoreInteractions(userRepositoryMock, reservationRepositoryMock);
    }

    @Test
    void testUpdateReservationInvalidUser() {
        // Arrange
        UpdateReservationRequest request = createSampleUpdateReservationRequest();
        ReservationEntity existingReservation = createSampleReservationEntity();

        when(reservationRepositoryMock.findByReservationId(request.getReservationId())).thenReturn(Optional.of(existingReservation));
        when(userRepositoryMock.findById(request.getUserId())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(InvalidReservationException.class, () -> updateReservationUseCase.updateReservation(request));

        // Verify that methods were called with the correct arguments
        verify(reservationRepositoryMock).findByReservationId(request.getReservationId());
        verify(userRepositoryMock).findById(request.getUserId());
        verifyNoMoreInteractions(reservationRepositoryMock);
    }

    // Helper methods to create sample objects
    private UpdateReservationRequest createSampleUpdateReservationRequest() {
        return UpdateReservationRequest.builder()
                .reservationId(1L)
                .userId(2L)
                .eventType(Event.WEDDING)
                .reservationCreatedDate(LocalDateTime.now())
                .reservationDate(LocalDateTime.now().plusDays(1))
                .startTime(LocalTime.of(10, 0))
                .endTime(LocalTime.of(12, 0))
                .build();
    }

    private ReservationEntity createSampleReservationEntity() {
        return ReservationEntity.builder()
                .reservationId(1L)
                .user(createSampleUserEntity())
                .eventType(Event.WEDDING)
                .reservationCreatedDate(LocalDateTime.now())
                .reservationDate(LocalDateTime.now().plusDays(1))
                .startTime(LocalTime.of(10, 0))
                .endTime(LocalTime.of(12, 0))
                .build();
    }

    private UserEntity createSampleUserEntity() {
        return UserEntity.builder()
                .id(2L)
                .firstName("SampleFirstName")
                .lastName("SampleLastName")
                .email("sample@example.com")
                .phoneNumber("123456789")
                .password("samplePassword")
                .role(Role.CUSTOMER)
                .build();
    }
}
