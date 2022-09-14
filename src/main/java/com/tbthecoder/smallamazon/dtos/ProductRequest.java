package com.tbthecoder.smallamazon.dtos;

public
record
ProductRequest
        (String sellerId,
         String productName,
         String productDesc,
         Double price,
         Integer stockQty,
         String category,
         String image) {
}
