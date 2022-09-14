package com.tbthecoder.smallamazon.controller.utils;

import com.tbthecoder.smallamazon.dtos.RegisterRequest;
import com.tbthecoder.smallamazon.exceptions.EmailExistsException;
import com.tbthecoder.smallamazon.exceptions.PasswordMisMatchException;

public class Validator {
    public static void validatePassword(RegisterRequest request) throws PasswordMisMatchException {
        if (!request.password().equals(request.repeat_password()))
            throw new PasswordMisMatchException("password mismatch");
        if (!request.password().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$"))
            throw new PasswordMisMatchException("password must contain at least one digit, one uppercase," +
                    " one lowercase, one special character and must be at least 8 characters long");
    }

    public static void validateEmail(RegisterRequest request) throws EmailExistsException {
        if (!request.email().matches("^[A-Za-z0-9+_.-]+@(.+)$")) throw new EmailExistsException("Invalid email");
    }
}
