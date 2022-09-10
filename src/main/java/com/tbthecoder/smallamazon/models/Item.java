package com.tbthecoder.smallamazon.models;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@Builder
public class Item {
    @Id
    private String itemId;
    @DBRef
    private Product product;
    private Integer quantity;
    private Double amountTotal;
}
