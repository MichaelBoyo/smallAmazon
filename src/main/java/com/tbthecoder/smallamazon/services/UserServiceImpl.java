package com.tbthecoder.smallamazon.services;

import com.tbthecoder.smallamazon.dtos.*;
import com.tbthecoder.smallamazon.models.Roles;
import com.tbthecoder.smallamazon.models.User;
import com.tbthecoder.smallamazon.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


import static com.tbthecoder.smallamazon.dtos.Status.*;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {

        if(userRepository.existsByEmail(registerRequest.email())){
            return new RegisterResponse(FAILURE, "Email already exists");
        }
        userRepository.save(User.builder()
                .email(registerRequest.email())
                .firstName(registerRequest.firstName())
                .lastName(registerRequest.lastName())
                .password(registerRequest.password())
                        .roles(Set.of(Roles.BUYER))
                .build());
        return new RegisterResponse(SUCCESS,"user registered successfully");
    }

    private User get(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
    }

    @Override
    public UserResponse getUser(String id) {
        return get(id).toUserResponse();
    }

    @Override
    public Response deleteUser(String id) {
        userRepository.delete(get(id));
        return new Response("user deleted successfully");
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(User::toUserResponse).toList();
    }

}
