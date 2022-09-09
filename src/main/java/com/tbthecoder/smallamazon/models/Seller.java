package com.tbthecoder.smallamazon.models;

import lombok.Builder;
import lombok.Data;

import java.util.Set;
@Builder
@Data
public class Seller extends User {
    private Store store;
    private Set<Product> products;
    private String storeName;
    private String storeDescription;
    private String storeAddress;
    private String storePhoneNumber;
    private String storeEmail;
    private String storeImage;

    public Seller(String id, String firstName, String lastName, String email, String password, Set<Roles> roles) {
        super(id, firstName, lastName, email, password, roles);
    }
}
