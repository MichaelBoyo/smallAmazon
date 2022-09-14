package com.tbthecoder.smallamazon.services.interfaces;

import com.tbthecoder.smallamazon.dtos.*;
import com.tbthecoder.smallamazon.exceptions.*;
import com.tbthecoder.smallamazon.models.Cart;
import com.tbthecoder.smallamazon.models.Customer;
import com.tbthecoder.smallamazon.models.User;


import java.util.List;

public interface CustomerService {
    RegisterResponse registerCustomer(RegisterRequest sellerRequest) throws EmailExistsException, PasswordMisMatchException;

    CustomerResponse getCustomer(String id) throws UserNotFoundException;

    Response deleteCustomer(String id) throws UserNotFoundException;

    List<CustomerResponse> getAllCustomers();
    void buildCustomerData(RegisterRequest request, User customer);

    Response deleteAllCustomers();


    Response orderItems(OrderRequest request) throws UserNotFoundException, ProductNotFoundException, OutOfStockException, InvalidQuantityException;

    CustomerResponse geCustomerByEmail(String email);

    Response makeAdmin(String id) throws UserNotFoundException;

    Cart getCustomerCart(String id) throws UserNotFoundException;
}
