package com.example.rivaconceptproject.configuration.db;

import com.example.rivaconceptproject.domain.enums.Role;
import com.example.rivaconceptproject.persistence.UserRepository;
import com.example.rivaconceptproject.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class DatabaseDataInitializer {
    private UserRepository userRepository;
//    private ReservationRepository reservationRepository;

    @EventListener(ApplicationReadyEvent.class)  //When the application is runned this will be executed.
    public void populateDatabaseInitialDummyData() {
        System.out.println("***** User has been added to system *****"); //To see what happens on console to understand.(Not needed)
            userRepository.save(UserEntity.builder()
                    .firstName("Eren")
                    .lastName("Destan")
                    .email("erendestan6@gmail.com")
                    .phoneNumber(31684469019L)
                    .password("123456eren")
                    .role(Role.Admin)
                    .build());

//        System.out.println("***** Reservation has been added to system *****");
//            reservationRepository.save(ReservationEntity.builder()
////                    .userId(1)
//                    .user()
//                    .eventType(Event.Wedding)
//                    .reservationCreatedDate(LocalDateTime.of(2023, 10, 27, 10, 33, 0))
//                    .reservationDate(LocalDateTime.of(2024, 6, 1, 18, 0, 0))
//                    .startTime(LocalTime.of(18, 0, 0))
//                    .endTime(LocalTime.of(23, 0, 0))
//                    .build());

    }
}
