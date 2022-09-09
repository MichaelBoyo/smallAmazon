package com.tbthecoder.smallamazon.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Data
@Document
@Builder
public class Store {
    @Id
    private String id;
    private String location;
    private String name;
    private String image;
    private String description;
    @DBRef
    private Set<Product> products;

}
