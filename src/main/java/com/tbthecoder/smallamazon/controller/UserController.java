package com.tbthecoder.smallamazon.controller;

import com.tbthecoder.smallamazon.dtos.RegisterRequest;
import com.tbthecoder.smallamazon.dtos.RegisterResponse;
import com.tbthecoder.smallamazon.dtos.Response;
import com.tbthecoder.smallamazon.dtos.UserResponse;
import com.tbthecoder.smallamazon.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @PostMapping
    public RegisterResponse register(@RequestBody RegisterRequest registerRequest) {
        log.info("registering user {}", registerRequest);
        return userService.register(registerRequest);
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable String id) {
        log.info("getting user {}", id);
        return userService.getUser(id);
    }

    @DeleteMapping("/{id}")
    public Response deleteUser(@PathVariable String id) {
        log.info("deleting user {}", id);
        return userService.deleteUser(id);
    }

    @GetMapping("/all")
    public List<UserResponse> getAllUsers() {
        log.info("getting all users");
        return userService.getAllUsers();
    }
}
