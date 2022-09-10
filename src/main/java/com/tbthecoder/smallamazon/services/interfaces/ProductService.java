package com.tbthecoder.smallamazon.services.interfaces;

import com.tbthecoder.smallamazon.dtos.AddProductRequest;
import com.tbthecoder.smallamazon.dtos.Response;
import com.tbthecoder.smallamazon.exceptions.ProductNotFoundException;
import com.tbthecoder.smallamazon.models.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(AddProductRequest addProductRequest);
    Product getProduct(String id) throws ProductNotFoundException;
    Response deleteProduct(String id) throws ProductNotFoundException;

    List<Product> findAllProducts();

    Product save(Product product);

    void deleteAll();
}
