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

    @EventListener(ApplicationReadyEvent.class)  //When the application is runned this will be executed.
    public void populateDatabaseInitialDummyData() {
        //throws ParseException
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date parsedDate = dateFormat.parse("2003-01-03");
        System.out.println("***** User has been added to system *****"); //To see what happens on console to understand.(Not needed)
            userRepository.save(UserEntity.builder()
                    .firstName("Eren")
                    .lastName("Destan")
                    .email("erendestan6@gmail.com")
                    .phoneNumber(31684469019L)
                    .role(Role.Admin)
                    .build());
            //                    .birthDate(parsedDate)
//        if (userRepository.count() == 0){
//            userRepository.save(UserEntity.builder()
//                    .id(1L)
//                    .firstName("Eren")
//                    .lastName("Destan")
//                    .email("erendestan6@gmail.com")
//                    .phoneNumber(31684469019L)
//                    .role(Role.Admin)
//                    .build());
//            //                    .birthDate(parsedDate)
//        }
    }
}
