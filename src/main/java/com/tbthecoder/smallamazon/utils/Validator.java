package com.tbthecoder.smallamazon.utils;

import com.tbthecoder.smallamazon.dtos.UserRequest;
import com.tbthecoder.smallamazon.exceptions.EmailException;
import com.tbthecoder.smallamazon.exceptions.PasswordException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Validator {
    public static void validatePassword(UserRequest request) throws PasswordException {
        if (!request.password().equals(request.repeat_password())) throw new PasswordException("password mismatch");
        if (!request.password().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$"))
            throw new PasswordException("password must contain at least one digit, one uppercase," +
                    " one lowercase, one special character and must be at least 8 characters long");
    }

    public static void validateEmail(UserRequest request) throws EmailException {
        if(request.email() ==null || request.email().isEmpty()) throw new EmailException("email cannot be empty");
        if(!request.email().contains(".")) throw new EmailException("email must  contain a dot");
        if (!request.email().matches("^[A-Za-z0-9+_.-]+@(.+)$")) throw new EmailException("Invalid email");
    }
}
