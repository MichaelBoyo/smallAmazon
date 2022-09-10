package com.tbthecoder.smallamazon.dtos;

import com.tbthecoder.smallamazon.models.Store;

public record SellerResponse(String id,String firstName, String lastName, String email, String phoneNumber, Store store) {

}
