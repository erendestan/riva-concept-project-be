package com.example.rivaconceptproject.business.impl.UserUseCaseImpls;

import com.example.rivaconceptproject.business.UserUseCases.UpdateUserUserCase;
import com.example.rivaconceptproject.business.exception.InvalidUserException;
import com.example.rivaconceptproject.domain.User.UpdateUserRequest;
import com.example.rivaconceptproject.persistence.UserRepository;
import com.example.rivaconceptproject.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateUserUseCaseImp implements UpdateUserUserCase {
    private final UserRepository userRepository;
    @Override
    public void updateUser(UpdateUserRequest request) {
        Optional<UserEntity> userOptional = userRepository.findUserById(request.getId());
        if (userOptional.isEmpty()){
            throw new InvalidUserException("USER_ID_INVALID");
        }

        UserEntity user = userOptional.get();
        updateFields(request, user);
    }

    private void updateFields(UpdateUserRequest request, UserEntity user){
        Optional userIdOptional = userRepository.findUserById(request.getId());

        if(userIdOptional.isPresent()){
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setEmail(request.getEmail());
            user.setPhoneNumber(request.getPhoneNumber());
            user.setPassword(request.getPassword());
            user.setRole(request.getRole());

            userRepository.save(user);
        }
        else{
            throw new InvalidUserException("USER_ID_INVALID");
        }


    }
}
