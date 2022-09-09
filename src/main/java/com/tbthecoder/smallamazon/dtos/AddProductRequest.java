package com.tbthecoder.smallamazon.dtos;

public record AddProductRequest(String sellerId, String productName,
                                String productDesc,
                                Double price,
                                Integer stockQty,
                                String category,
                                String image) {
}
