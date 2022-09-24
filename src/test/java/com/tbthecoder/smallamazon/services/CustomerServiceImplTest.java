package com.tbthecoder.smallamazon.services;

import com.tbthecoder.smallamazon.dtos.*;
import com.tbthecoder.smallamazon.exceptions.*;
import com.tbthecoder.smallamazon.models.Product;
import com.tbthecoder.smallamazon.services.interfaces.CustomerService;
import com.tbthecoder.smallamazon.services.interfaces.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class CustomerServiceImplTest {
    @Autowired
    private CustomerService customerService;
    private RegisterResponse res;
    private UserRequest request;


    @BeforeEach
    void setUp() throws EmailException, PasswordException {
        request = new UserRequest(
                "mikee@gmail.com",
                "MutiuPamilerin96$",
                "Michael",
                "Boyo",
                "08103297538",
                "MutiuPamilerin96$");
        res = customerService.registerCustomer(request);
    }

    @Test
    void throwEmailExceptionWhenEmailIsEmpty() {
        assertThrows(EmailException.class, () -> customerService.registerCustomer(new UserRequest(
                "",
                "MutiuPamilerin96#",
                "Michael",
                "Boyo",
                "08103297538",
                "MutiuPamilerin96#"
        )));

    }
    @Test
    void throwEmailExceptionWhenEmailIsInvalid() {
        assertThrows(EmailException.class, () -> customerService.registerCustomer(new UserRequest(
                "boyomichaelbidemi@gmail",
                "MutiuPamilerin96#",
                "Michael",
                "Boyo",
                "08103297538",
                "MutiuPamilerin96#")));
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
    void getCustomer() throws UserNotFoundException {
        var customer = customerService.getCustomerByEmail("mikee@gmail.com");
        assertNotNull(customer);
        assertEquals("Michael", customer.firstName());
    }

    @Test
    void deleteCustomer() throws UserNotFoundException {
        var customer = customerService.getCustomerByEmail("mikee@gmail.com");

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
        product.setStockAvailable(45);
        product.setPrice(250.0);
        product.setName("bag");
        product = productService.save(product);
        var customer = customerService.getCustomerByEmail("mikee@gmail.com");

        customerService.orderItems(new OrderRequest(12, customer.id(), product.getId()));
        customer = customerService.getCustomerByEmail("mikee@gmail.com");

        assertEquals(customer.cart().getProducts().size(),1);

    }
}