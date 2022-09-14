package com.tbthecoder.smallamazon.dtos;

import com.tbthecoder.smallamazon.models.Product;

public
record
ItemDTO(Product product,
        Integer quantity,
        Double amountTotal) {
}
