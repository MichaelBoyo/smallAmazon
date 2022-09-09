package com.tbthecoder.smallamazon.models;

import com.tbthecoder.smallamazon.dtos.UserResponse;

import lombok.Data;


import java.util.HashSet;
import java.util.Set;

@Data
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private Set<Roles> roles;

    public void addRole(Roles role) {
        if (roles == null) roles = new HashSet<>();
        roles.add(role);
    }

    public UserResponse toUserResponse() {
        return new UserResponse(firstName, lastName, email);
    }
}
