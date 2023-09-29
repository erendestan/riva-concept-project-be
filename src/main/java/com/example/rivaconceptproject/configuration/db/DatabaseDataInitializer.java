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
        System.out.println("***** User has been added to system *****"); //To see what happens on console to understand.(Not needed)
            userRepository.save(UserEntity.builder()
                    .firstName("Eren")
                    .lastName("Destan")
                    .email("erendestan6@gmail.com")
                    .phoneNumber(31684469019L)
                    .role(Role.Admin)
                    .build());

    }
}
