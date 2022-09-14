package com.tbthecoder.smallamazon.controller;

import com.tbthecoder.smallamazon.dtos.CustomerResponse;
import com.tbthecoder.smallamazon.dtos.OrderRequest;
import com.tbthecoder.smallamazon.dtos.RegisterRequest;
import com.tbthecoder.smallamazon.exceptions.*;
import com.tbthecoder.smallamazon.services.interfaces.CustomerService;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> registerCustomer(@RequestBody RegisterRequest request) throws EmailExistsException, PasswordMisMatchException {
        log.info("registering user {}", request);
        return new ResponseEntity<>(customerService.registerCustomer(request), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    @GetMapping("/all")
    public List<CustomerResponse> findAlCustomers() {
        log.info("getting all customers");
        return customerService.getAllCustomers();
    }
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    @DeleteMapping("/all")
    public ResponseEntity<?> deleteAllCustomers() {
        log.info("deleting all customers");
        return new ResponseEntity<>(customerService.deleteAllCustomers(), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping("/order")
    public ResponseEntity<?> orderItem(@RequestBody OrderRequest request) throws UserNotFoundException, ProductNotFoundException, OutOfStockException, InvalidQuantityException {
        return new ResponseEntity<>(customerService.orderItems(request), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    @PatchMapping("/make-admin/{id}")
    public ResponseEntity<?> makeAdmin(@PathVariable String id) throws UserNotFoundException {
        return new ResponseEntity<>(customerService.makeAdmin(id), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping("/cart/{id}")
    public ResponseEntity<?> getCart(@PathVariable String id) throws UserNotFoundException {
        return new ResponseEntity<>(customerService.getCustomerCart(id), HttpStatus.OK);
    }
}
