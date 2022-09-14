package com.tbthecoder.smallamazon.controller;

import com.tbthecoder.smallamazon.dtos.*;
import com.tbthecoder.smallamazon.exceptions.EmailExistsException;
import com.tbthecoder.smallamazon.exceptions.PasswordMisMatchException;
import com.tbthecoder.smallamazon.exceptions.StoreNameTakenException;
import com.tbthecoder.smallamazon.exceptions.UserNotFoundException;
import com.tbthecoder.smallamazon.services.interfaces.SellerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tbthecoder.smallamazon.dtos.Status.FAILURE;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/seller")
@Slf4j
@AllArgsConstructor
public class SellerController {
    private final SellerService sellerService;

    @PostMapping
    public RegisterResponse register(@RequestBody SellerRequest sellerRequest) throws Exception {
        log.info("registering seller");
        return sellerService.register(sellerRequest);
    }

    @PreAuthorize("hasAnyRole('ROLE_SELLER', 'ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getSeller(@PathVariable String id) throws UserNotFoundException {
        log.info("getting seller {}", id);
        return new ResponseEntity<>(sellerService.getSeller(id), HttpStatus.OK);


    }

    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public Response deleteSeller(@PathVariable String id) throws UserNotFoundException {
        log.info("deleting seller {}", id);
        return sellerService.deleteSeller(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public List<SellerResponse> getAllSellers() {
        log.info("getting all sellers");
        return sellerService.getAllSellers();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/all-sellers")
    public Response deleteAllSellers() {
        log.info("deleting all sellers");
        return sellerService.deleteAllSellers();
    }

    @PreAuthorize("hasRole('ROLE_SELLER' )")
    @PutMapping
    public ResponseEntity<?> addProduct(@RequestBody ProductRequest request) throws UserNotFoundException {
        log.info("adding product -> {}", request);
        return new ResponseEntity<>(sellerService.addProduct(request), HttpStatus.OK);

    }
}
