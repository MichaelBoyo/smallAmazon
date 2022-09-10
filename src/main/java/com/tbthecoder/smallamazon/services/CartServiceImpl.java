package com.tbthecoder.smallamazon.services;

import com.tbthecoder.smallamazon.dtos.RegisterRequest;
import com.tbthecoder.smallamazon.dtos.Response;
import com.tbthecoder.smallamazon.exceptions.CartNotFoundException;
import com.tbthecoder.smallamazon.models.Cart;
import com.tbthecoder.smallamazon.repositories.CartRepository;
import com.tbthecoder.smallamazon.services.interfaces.CartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

import static com.tbthecoder.smallamazon.dtos.Status.SUCCESS;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    @Override
    public Cart saveCart(RegisterRequest request) {
         return cartRepository.save(Cart.builder()
                         .products(new ArrayList<>())
                 .build());
    }

    @Override
    public Cart getCart(String id) throws CartNotFoundException {
        return cartRepository.findById(id).orElseThrow(()-> new CartNotFoundException("cart not found"));
    }

    @Override
    public Response deleteCart(String id) throws CartNotFoundException {
        cartRepository.delete(getCart(id));
        return new Response(SUCCESS,"Cart Deleted Successfully");
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public void clearDataBase() {
        cartRepository.deleteAll();
    }
}
