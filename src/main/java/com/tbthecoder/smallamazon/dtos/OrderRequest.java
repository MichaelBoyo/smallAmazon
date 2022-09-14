package com.tbthecoder.smallamazon.dtos;

public
record
OrderRequest(
        Integer quantity,
        String customerId,
        String productId) {
}
