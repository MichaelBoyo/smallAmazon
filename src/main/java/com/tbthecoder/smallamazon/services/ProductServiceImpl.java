package com.tbthecoder.smallamazon.services;

import com.tbthecoder.smallamazon.dtos.AddProductRequest;
import com.tbthecoder.smallamazon.models.Product;
import com.tbthecoder.smallamazon.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    @Override
    public Product saveProduct(AddProductRequest addProductRequest) {
        return productRepository.save(Product.builder()
                .name(addProductRequest.productName())
                .description(addProductRequest.productDesc())
                .price(addProductRequest.price())
                .stockQty(addProductRequest.stockQty())
                .category(addProductRequest.category())
                .image(addProductRequest.image())
                .build());
    }
}
