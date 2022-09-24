package com.tbthecoder.smallamazon.dtos;

public
record
UserRequest(String email,
            String password,
            String firstname,
            String lastname,
            String phoneNumber,
            String repeat_password) {
    public UserRequest(String email, String password, String firstname, String lastname, String phoneNumber, String repeat_password) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.repeat_password = repeat_password;

    }
}

