package com.tbthecoder.smallamazon.services.interfaces;

import com.tbthecoder.smallamazon.dtos.*;
import com.tbthecoder.smallamazon.exceptions.*;
import com.tbthecoder.smallamazon.models.Cart;


import java.util.List;

public interface CustomerService {
    RegisterResponse registerCustomer(RegisterRequest sellerRequest) throws EmailExistsException;

    CustomerResponse getCustomer(String id) throws UserNotFoundException;

    Response deleteCustomer(String id) throws UserNotFoundException;

    List<CustomerResponse> getAllCustomers();

    Response deleteAllCustomers();

    Response orderItems(OrderRequest request) throws UserNotFoundException, ProductNotFoundException, OutOfStockException, InvalidQuantityException;

    CustomerResponse geCustomerByEmail(String mikee);
}
