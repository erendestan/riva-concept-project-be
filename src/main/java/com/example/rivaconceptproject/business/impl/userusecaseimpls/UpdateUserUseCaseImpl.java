package com.example.rivaconceptproject.business.impl.userusecaseimpls;

import com.example.rivaconceptproject.business.userusecases.UpdateUserUserCase;
import com.example.rivaconceptproject.business.exception.InvalidUserException;
import com.example.rivaconceptproject.domain.user.UpdateUserRequest;
import com.example.rivaconceptproject.persistence.UserRepository;
import com.example.rivaconceptproject.persistence.entity.UserEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUserCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    @Override
    public void updateUser(UpdateUserRequest request) {
        Optional<UserEntity> userOptional = userRepository.findById(request.getId());
        if (userOptional.isEmpty()){
            throw new InvalidUserException("USER_ID_INVALID");
        }

        UserEntity user = userOptional.get();
        updateFields(request, user);
    }

    private void updateFields(UpdateUserRequest request, UserEntity user){
//        String encodedPassword = passwordEncoder.encode(request.getPassword());

        Optional<UserEntity> userIdOptional = userRepository.findById(request.getId());

        // Check if the new password is different from the existing one
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            // Only update the password if it has changed
            String encodedPassword = passwordEncoder.encode(request.getPassword());
            user.setPassword(encodedPassword);
        }

        if(userIdOptional.isPresent()){
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setEmail(request.getEmail());
            user.setPhoneNumber(request.getPhoneNumber());
//            user.setPassword(encodedPassword);
//            user.setPassword(request.getPassword());
            user.setRole(request.getRole());
            user.setActive(request.isActive());
            userRepository.save(user);
        }
        else{
            throw new InvalidUserException("USER_ID_INVALID");
        }


    }
}
