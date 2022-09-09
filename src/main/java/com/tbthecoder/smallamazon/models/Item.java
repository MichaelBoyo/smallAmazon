package com.tbthecoder.smallamazon.models;


import lombok.Data;

@Data
public class Item {
    private Product product;
    private int quantity;
}
