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
import org.junit.Test;
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
public class UpdateReservationUseCaseImplTest {
    @Mock
    private ReservationRepository reservationRepositoryMock;
    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private UpdateReservationUseCaseImpl updateReservationUseCase;

    @Test
    public void testUpdateReservation() {
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
    public void testUpdateReservationInvalidReservationId() {
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
    public void testUpdateReservationInvalidUser() {
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
//    @Mock
//    ReservationRepository reservationRepositoryMock;
//    @Mock
//    UserRepository userRepositoryMock;
//    @InjectMocks
//    UpdateReservationUseCaseImpl updateReservationUseCase;
//
//    @BeforeEach
//    void setUp() {
//        UserEntity savedUser = UserEntity.builder()
//                .id(1L)
//                .firstName("Jack")
//                .lastName("Kral")
//                .email("jackkral@gmail.com")
//                .phoneNumber("555444111")
//                .password("testpassword")
//                .role(Role.CUSTOMER)
//                .build();
//
//        ReservationEntity savedReservation = ReservationEntity.builder()
//                .reservationId(1L)
//                .user(savedUser)
//                .eventType(Event.WEDDING)
//                .reservationCreatedDate(LocalDateTime.of(2022, 11, 15, 18, 0))
//                .reservationDate(LocalDateTime.of(2023, 11, 15, 18, 0))
//                .startTime(LocalTime.of(18, 0))
//                .endTime(LocalTime.of(23, 0))
//                .build();
//
//        when(reservationRepositoryMock.findByReservationId(1L)).thenReturn(Optional.of(savedReservation));
//
//        when(reservationRepositoryMock.findByReservationId(2L)).thenReturn(Optional.empty());
//    }
//
//
//        @Test
//        void updateReservationShouldUpdateReservation() {
//            UpdateReservationRequest updateReservationRequest = new UpdateReservationRequest();
//            updateReservationRequest.setReservationId(1L);
//            updateReservationRequest.setEventType(Event.OTHER);
//            updateReservationRequest.setReservationCreatedDate(LocalDateTime.of(2021, 11, 15, 18, 0));
//            updateReservationRequest.setReservationDate(LocalDateTime.of(2024, 11, 15, 18, 0));
//            updateReservationRequest.setStartTime(LocalTime.of(20, 0));
//            updateReservationRequest.setEndTime(LocalTime.of(0, 0));
//
//            updateReservationUseCase.updateReservation(updateReservationRequest);
//
//            // Verify that the repository.save method is called once with the correct argument
//            verify(reservationRepositoryMock, times(1)).save(any());
//
//            // Optionally, you can use ArgumentCaptor to capture the actualReservation
//            ArgumentCaptor<ReservationEntity> reservationCaptor = ArgumentCaptor.forClass(ReservationEntity.class);
//            verify(reservationRepositoryMock).save(reservationCaptor.capture());
//
//            // Now, you can perform assertions on the captured reservation if needed
//            ReservationEntity capturedReservation = reservationCaptor.getValue();
//            assertNotNull(capturedReservation);
//
//            // Additional assertions...
//            assertEquals(Event.OTHER, capturedReservation.getEventType());
//            assertEquals(LocalDateTime.of(2021, 11, 15, 18, 0), capturedReservation.getReservationCreatedDate());
//            assertEquals(LocalDateTime.of(2024, 11, 15, 18, 0), capturedReservation.getReservationDate());
//            assertEquals(LocalTime.of(20, 0), capturedReservation.getStartTime());
//            assertEquals(LocalTime.of(0, 0), capturedReservation.getEndTime());
//    }

}
