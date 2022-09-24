package com.tbthecoder.smallamazon.services;

import com.tbthecoder.smallamazon.dtos.UserRequest;
import com.tbthecoder.smallamazon.exceptions.CartNotFoundException;
import com.tbthecoder.smallamazon.models.Cart;
import com.tbthecoder.smallamazon.services.interfaces.CartService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CartServiceImplTest {
    @Autowired
    private CartService cartService;
    private Cart cart;

    @BeforeEach
    void setUp() {
        cart = cartService.saveCart(new UserRequest(
                "testemail",
                "testpassword",
                "testfirstname",
                "testlastname",
                "testphone",
                "testpassword"
        ));
    }

    @AfterEach
    void tearDown(){
        cartService.clearDataBase();
    }


    @Test
    void saveCart() {
        assertNotNull(cartService);
        assertFalse(cartService.getAllCarts().isEmpty());
    }

    @Test
    void getCart() throws CartNotFoundException {
        assertNotNull(cartService.getCart(cart.getCartId()));
    }

    @Test
    void deleteCart() throws CartNotFoundException {
        cartService.deleteCart(cart.getCartId());
        assertEquals(0,cartService.getAllCarts().size());
    }

    @Test
    void getAllCarts() {
        assertEquals(1, cartService.getAllCarts().size());
    }

    @Test
    void save() {
        cartService.save(cart);
        assertNotNull(cart);
    }
}