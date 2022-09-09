package com.tbthecoder.smallamazon.services;

import com.tbthecoder.smallamazon.dtos.AddProductRequest;
import com.tbthecoder.smallamazon.models.Product;

public interface ProductService {
    Product saveProduct(AddProductRequest addProductRequest);
}
