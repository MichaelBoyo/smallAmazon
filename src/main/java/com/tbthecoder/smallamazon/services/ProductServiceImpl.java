package com.tbthecoder.smallamazon.services;

import com.tbthecoder.smallamazon.dtos.ProductRequest;
import com.tbthecoder.smallamazon.dtos.Response;
import com.tbthecoder.smallamazon.dtos.Status;
import com.tbthecoder.smallamazon.exceptions.ProductNotFoundException;
import com.tbthecoder.smallamazon.models.Product;
import com.tbthecoder.smallamazon.repositories.ProductRepository;
import com.tbthecoder.smallamazon.services.interfaces.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product saveProduct(ProductRequest productRequest) {
        return productRepository.save(Product.builder()
                .name(productRequest.productName())
                .description(productRequest.productDesc())
                .price(productRequest.price())
                .stockAvailable(productRequest.stockQty())
                .category(productRequest.category())
                .image(productRequest.image())
                .build());
    }

    @Override
    public Product getProduct(String id) throws ProductNotFoundException {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("product not found"));
    }

    @Override
    public Response deleteProduct(String id) throws ProductNotFoundException {
        productRepository.delete(getProduct(id));
        return new Response(Status.DELETE_SUCCESS, "Deleted Successfully");
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product save(Product product) {
      return   productRepository.save(product);
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }
}
