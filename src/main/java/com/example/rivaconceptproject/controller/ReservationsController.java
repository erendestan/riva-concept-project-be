package com.example.rivaconceptproject.controller;


import com.example.rivaconceptproject.business.ReservationUseCases.*;
import com.example.rivaconceptproject.domain.Reservation.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/reservations")
@AllArgsConstructor
//@CrossOrigin(origins = "http://127.0.0.1:5173/")
public class ReservationsController {
    private final GetReservationUseCase getReservationUseCase;
    private final GetReservationsUseCase getReservationsUseCase;
    private final CreateReservationUseCase createReservationUseCase;
    private final DeleteReservationUseCase deleteReservationUseCase;
    private final UpdateReservationUseCase updateReservationUseCase;

    @GetMapping("{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable(value = "id") final long id){
        final Optional<Reservation> reservationOptional = getReservationUseCase.getReservation(id);
        if(reservationOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(reservationOptional.get());
    }

    @GetMapping
    public ResponseEntity<GetAllReservationsResponse> getAllReservations(){
        return ResponseEntity.ok(getReservationsUseCase.getReservations());
    }

    @PostMapping
    public ResponseEntity<CreateReservationResponse> createReservation(@RequestBody @Valid CreateReservationRequest request){
        CreateReservationResponse response = createReservationUseCase.createReservation(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable int reservationId){
        deleteReservationUseCase.deleteReservation(reservationId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{reservationId}")
    public ResponseEntity<Void> updateReservation(@PathVariable("reservationId") long id,
                                                  @RequestBody @Valid UpdateReservationRequest request){
        request.setReservationId(id);
        updateReservationUseCase.updateReservation(request);
        return ResponseEntity.noContent().build();
    }
}
