package com.tbthecoder.smallamazon.controller;

import com.tbthecoder.smallamazon.dtos.AddProductRequest;
import com.tbthecoder.smallamazon.exceptions.ProductNotFoundException;
import com.tbthecoder.smallamazon.services.interfaces.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/product")
@AllArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody AddProductRequest request) {
        log.info("adding product {}", request);
        return new ResponseEntity<>(productService.saveProduct(request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) throws ProductNotFoundException {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts() {
        return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
    }
}
