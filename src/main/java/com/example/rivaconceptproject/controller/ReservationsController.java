package com.example.rivaconceptproject.controller;


import com.example.rivaconceptproject.business.reservationusecases.*;
import com.example.rivaconceptproject.domain.enums.Event;
import com.example.rivaconceptproject.domain.reservation.*;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
@AllArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5173/")
public class ReservationsController {
    private final GetReservationUseCase getReservationUseCase;
    private final GetReservationsUseCase getReservationsUseCase;
    private final CreateReservationUseCase createReservationUseCase;
    private final DeleteReservationUseCase deleteReservationUseCase;
    private final UpdateReservationUseCase updateReservationUseCase;
    private final GetFilteredReservationsUseCase getFilteredReservationsUseCase;

    @RolesAllowed({"CUSTOMER", "ADMIN"})
    @GetMapping("{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable(value = "id") final long id){
        final Optional<Reservation> reservationOptional = getReservationUseCase.getReservation(id);
        if(reservationOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(reservationOptional.get());
    }
    @RolesAllowed({"CUSTOMER", "ADMIN"})
    @GetMapping
    public ResponseEntity<GetAllReservationsResponse> getAllReservations(){
        return ResponseEntity.ok(getReservationsUseCase.getReservations());
    }

    @RolesAllowed({"CUSTOMER", "ADMIN"})
    @PostMapping
    public ResponseEntity<CreateReservationResponse> createReservation(@RequestBody @Valid CreateReservationRequest request){
        CreateReservationResponse response = createReservationUseCase.createReservation(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @RolesAllowed({"CUSTOMER", "ADMIN"})
    @DeleteMapping("{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable int reservationId){
        deleteReservationUseCase.deleteReservation(reservationId);
        return ResponseEntity.noContent().build();
    }

    @RolesAllowed({"CUSTOMER", "ADMIN"})
    @PutMapping("{reservationId}")
    public ResponseEntity<Void> updateReservation(@PathVariable("reservationId") long id,
                                                  @RequestBody @Valid UpdateReservationRequest request){
        request.setReservationId(id);
        updateReservationUseCase.updateReservation(request);
        return ResponseEntity.noContent().build();
    }

//    @RolesAllowed({"CUSTOMER", "ADMIN"})
//    @GetMapping("/filtered")
//    public ResponseEntity<GetFilteredReservationsResponse> getFilteredReservations(
//            @RequestParam(name = "eventType", required = false) Event eventType,
//            @RequestParam(name = "startDate", required = false) LocalDateTime startDate,
//            @RequestParam(name = "endDate", required = false) LocalDateTime endDate) {
//
//        GetFilteredReservationsRequest request = GetFilteredReservationsRequest.builder()
//                .eventType(eventType)
//                .startDate(startDate)
//                .endDate(endDate)
//                .build();
//
//        return ResponseEntity.ok(getFilteredReservationsUseCase.getFilteredReservations(request));
//    }

    @GetMapping("/filtered")
    public ResponseEntity<GetFilteredReservationsResponse> getFilteredReservations(
            @RequestParam(name = "eventType", required = false) Event eventType,
            @RequestParam(name = "startDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(name = "endDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        GetFilteredReservationsRequest request = GetFilteredReservationsRequest.builder()
                .eventType(eventType)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        return ResponseEntity.ok(getFilteredReservationsUseCase.getFilteredReservations(request));
    }
}
