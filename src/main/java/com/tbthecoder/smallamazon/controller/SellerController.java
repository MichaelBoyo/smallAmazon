package com.tbthecoder.smallamazon.controller;

import com.tbthecoder.smallamazon.dtos.*;
import com.tbthecoder.smallamazon.services.SellerNotFoundException;
import com.tbthecoder.smallamazon.services.SellerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tbthecoder.smallamazon.dtos.Status.FAILURE;

@RestController
@RequestMapping("/api/v1/seller")
@Slf4j
@AllArgsConstructor
public class SellerController {
    private final SellerService sellerService;

    @PostMapping
    public SellerRegisterResponse register(@RequestBody SellerRequest sellerRequest) {
        log.info("registering seller");
        return sellerService.register(sellerRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSeller(@PathVariable String id) {
        log.info("getting seller {}", id);
        try {
            return new ResponseEntity<>(sellerService.getSeller(id), HttpStatus.OK);
        } catch (Exception e) {
            log.error("error getting seller {}", id);
            return new ResponseEntity<>(new Response(FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
        }


    }

    @DeleteMapping("/{id}")
    public Response deleteSeller(@PathVariable String id) {
        return sellerService.deleteSeller(id);
    }

    @GetMapping("/all")
    public List<SellerResponse> getAllSellers() {
        log.info("getting all sellers");
        return sellerService.getAllSellers();
    }

    @DeleteMapping("/all-sellers")
    public Response deleteAllSellers() {
        return sellerService.deleteAllSellers();
    }


    @PutMapping
    public ResponseEntity<?> addProduct(@RequestBody AddProductRequest request) {
        try {
            return new ResponseEntity<>(sellerService.addProduct(request), HttpStatus.OK);
        } catch (SellerNotFoundException e) {
            log.error("error adding producr {}", request);
            return new ResponseEntity<>(new Response(FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
