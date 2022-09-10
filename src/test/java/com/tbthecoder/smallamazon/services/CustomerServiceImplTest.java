package com.tbthecoder.smallamazon.services;

import com.tbthecoder.smallamazon.dtos.*;
import com.tbthecoder.smallamazon.exceptions.*;
import com.tbthecoder.smallamazon.models.Product;
import com.tbthecoder.smallamazon.services.interfaces.CustomerService;
import com.tbthecoder.smallamazon.services.interfaces.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServiceImplTest {
    @Autowired
    private CustomerService customerService;
    private RegisterResponse res;


    @BeforeEach
    void setUp() throws EmailExistsException {
        res = customerService.registerCustomer(new RegisterRequest("mikee", "1234", "Michael", "Boyo", "08103297538"));
    }

    @AfterEach
    void tearDown() {
        customerService.deleteAllCustomers();
    }

    @Test
    void registerCustomer() {
        assertEquals(res.status(), Status.SUCCESS);
        assertEquals(customerService.getAllCustomers().size(), 1);
    }

    @Test
    void getCustomer() {
        var customer = customerService.geCustomerByEmail("mikee");
        assertNotNull(customer);
        assertEquals("Michael", customer.firstName());
    }

    @Test
    void deleteCustomer() throws UserNotFoundException {
        var customer = customerService.geCustomerByEmail("mikee");

        customerService.deleteCustomer(customer.id());
        assertEquals(0, customerService.getAllCustomers().size());
    }

    @Test
    void getAllCustomers() {
        assertEquals(1, customerService.getAllCustomers().size());
    }

    @Test
    void deleteAllCustomers() {
        customerService.deleteAllCustomers();
        assertEquals(Collections.emptyList(), customerService.getAllCustomers());
    }

    @Autowired
    private ProductService productService;

    @Test
    void orderItems() throws UserNotFoundException, OutOfStockException, InvalidQuantityException, ProductNotFoundException {
        var product = Product.builder().build();
        product.setStockQty(45);
        product.setPrice(250.0);
        product.setName("bag");
        product = productService.save(product);
        var customer = customerService.geCustomerByEmail("mikee");

        customerService.orderItems(new OrderRequest(12, customer.id(), product.getId()));
        customer = customerService.geCustomerByEmail("mikee");

        assertEquals(customer.cart().getProducts().size(),1);

    }
}