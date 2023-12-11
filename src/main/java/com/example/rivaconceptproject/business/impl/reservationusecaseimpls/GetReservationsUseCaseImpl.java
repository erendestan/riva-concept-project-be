package com.example.rivaconceptproject.business.impl.reservationusecaseimpls;

import com.example.rivaconceptproject.business.reservationusecases.GetReservationsUseCase;
import com.example.rivaconceptproject.business.exception.ReservationRetrivalException;
import com.example.rivaconceptproject.domain.reservation.GetAllReservationsResponse;
import com.example.rivaconceptproject.domain.reservation.Reservation;
import com.example.rivaconceptproject.persistence.ReservationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetReservationsUseCaseImpl implements GetReservationsUseCase {
    private final ReservationRepository reservationRepository;
    @Transactional
    @Override
    public GetAllReservationsResponse getReservations() {

        try{
            List<Reservation> reservations = reservationRepository.findAll()
                    .stream()
                    .map(ReservationConverter::convert)
                    .toList();

            return GetAllReservationsResponse.builder()
                    .reservations(reservations)
                    .build();
        }
        catch (Exception ex){
            throw new ReservationRetrivalException(ex.getMessage());
        }

    }
}
