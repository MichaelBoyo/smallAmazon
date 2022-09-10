package com.tbthecoder.smallamazon.models;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Getter
@Setter
@Builder
public class Cart {
    @Id
    private String cartId;
    @DBRef
    private List<Item> products;
}
