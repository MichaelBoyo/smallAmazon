package com.tbthecoder.smallamazon.services;

import com.tbthecoder.smallamazon.dtos.RegisterRequest;
import com.tbthecoder.smallamazon.dtos.RegisterResponse;
import com.tbthecoder.smallamazon.dtos.Response;
import com.tbthecoder.smallamazon.dtos.UserResponse;

import java.util.List;

public interface UserService{
    RegisterResponse register(RegisterRequest registerRequest);
    UserResponse getUser(String id);
    Response deleteUser(String id);
    List<UserResponse> getAllUsers();
}