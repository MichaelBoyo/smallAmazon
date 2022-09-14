package com.tbthecoder.smallamazon.dtos;

import com.tbthecoder.smallamazon.models.Cart;

public
record
CustomerResponse(String id,
                 String firstName,
                 String lastName,
                 String email,
                 String phoneNumber,
                 Cart cart) {
}
