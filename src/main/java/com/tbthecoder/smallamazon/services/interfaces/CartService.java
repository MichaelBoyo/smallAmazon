package com.tbthecoder.smallamazon.services.interfaces;

import com.tbthecoder.smallamazon.dtos.UserRequest;
import com.tbthecoder.smallamazon.dtos.Response;
import com.tbthecoder.smallamazon.exceptions.CartNotFoundException;
import com.tbthecoder.smallamazon.models.Cart;

import java.util.List;

public interface CartService {
    Cart saveCart (UserRequest request);
    Cart getCart(String id) throws CartNotFoundException;
    Response deleteCart(String id) throws CartNotFoundException;
    List<Cart> getAllCarts();

    void save(Cart cart);

    void clearDataBase();
}
