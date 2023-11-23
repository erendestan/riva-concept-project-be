package com.example.rivaconceptproject.controller;

import com.example.rivaconceptproject.business.userusecases.*;
import com.example.rivaconceptproject.domain.user.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController //Means Endpoint is defined here
@RequestMapping("/users") //And endpoint is connected to /users
@AllArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5173/")
public class UsersController {
    //Those are injected here! Which is object of another class
    private final GetUserUseCase getUserUseCase;
    private final GetUsersUseCase getUsersUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final UpdateUserUserCase updateUserUserCase;

    @GetMapping("id/{id}") //This is the getMapping for get info about specific user with id
    public ResponseEntity<User> getUser(@PathVariable(value = "id") final long id){
        final Optional<User> userOptional = getUserUseCase.getUser(id);
        if(userOptional.isEmpty()){
            return ResponseEntity.notFound().build(); //If nothing found with that id, return not found as response
        }
        return ResponseEntity.ok().body(userOptional.get());
    }

    @GetMapping("email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable(value = "email") final String email){
        final Optional<User> userOptional = getUserUseCase.getUserByEmail(email);
        if(userOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userOptional.get());
    }


    @GetMapping //This is the getMapping for get all the users
    public ResponseEntity<GetAllUsersResponse> getAllUsers(){
        //            (@RequestParam(value = "reservation", required = false) Long reservationId) --> for later use
        return ResponseEntity.ok(getUsersUseCase.getUsers());
    }

    @PostMapping //PostMapping for Create new user
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest request){
        CreateUserResponse response = createUserUseCase.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{userId}") //DeleteMapping for Deleting user with the provided id
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId){
        deleteUserUseCase.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{userId}") //PutMapping for updating the user with the provided id
    public ResponseEntity<Void> updateUser(@PathVariable("userId") long id,
                                           @RequestBody @Valid UpdateUserRequest request){
        request.setId(id);
        updateUserUserCase.updateUser(request);
        return ResponseEntity.noContent().build();
    }

}
