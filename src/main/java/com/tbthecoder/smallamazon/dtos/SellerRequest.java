package com.tbthecoder.smallamazon.dtos;

public
record
SellerRequest(String phoneNumber,
              String storeEmail,
              String password,
              String email,
              String storeImage,
              String storeAddress,
              String storeName,
              String storeDescription,
              String firstName,
              String lastName,
              String repeat_password) {

    public UserRequest toRegRequest() {
        return new UserRequest(email, password, firstName, lastName,phoneNumber,repeat_password);
    }
}



