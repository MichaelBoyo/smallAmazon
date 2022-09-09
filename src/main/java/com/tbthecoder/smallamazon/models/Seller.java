package com.tbthecoder.smallamazon.models;

import com.tbthecoder.smallamazon.dtos.SellerResponse;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Set;

@Builder
@Document
@Getter
@Setter
public class Seller extends User {
    @Id
    private String id;

    @DBRef
    private Store store;

    public SellerResponse toSellerResponse() {
        return new SellerResponse(id,store);
    }
}
