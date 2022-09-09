package com.tbthecoder.smallamazon.models;

import com.tbthecoder.smallamazon.dtos.UserResponse;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@Builder
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<Roles> roles;

    public void addRole(Roles role) {
        if (roles == null) roles = Set.of(role);
        roles.add(role);
    }

    public UserResponse toUserResponse() {
        return new UserResponse(id, firstName, lastName, email);
    }
}
