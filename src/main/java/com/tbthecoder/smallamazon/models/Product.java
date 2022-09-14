package com.tbthecoder.smallamazon.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@Builder
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private Double price;
    private Integer stockAvailable;
    private String category;
    private String image;
}
