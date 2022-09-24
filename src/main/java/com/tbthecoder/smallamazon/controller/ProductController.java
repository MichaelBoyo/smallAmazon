package com.tbthecoder.smallamazon.controller;

import com.tbthecoder.smallamazon.dtos.ProductRequest;
import com.tbthecoder.smallamazon.exceptions.ProductNotFoundException;
import com.tbthecoder.smallamazon.models.Product;
import com.tbthecoder.smallamazon.services.interfaces.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("api/v1/product")
@AllArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;
    @PreAuthorize("hasAnyRole('ADMIN', 'SELLER')")
    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody ProductRequest request) {
        log.info("adding product {}", request);
        return ResponseEntity.ok(productService.saveProduct(request));
    }
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) throws ProductNotFoundException {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(productService.findAllProducts());
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable String id) throws ProductNotFoundException {
        return ResponseEntity.ok(productService.getProduct(id));
    }

}
