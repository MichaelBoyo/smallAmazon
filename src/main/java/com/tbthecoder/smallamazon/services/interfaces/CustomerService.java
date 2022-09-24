package com.tbthecoder.smallamazon.services.interfaces;


import com.tbthecoder.smallamazon.dtos.*;
import com.tbthecoder.smallamazon.exceptions.*;
import com.tbthecoder.smallamazon.models.Cart;
import com.tbthecoder.smallamazon.models.User;


import java.util.List;

public interface CustomerService {
    RegisterResponse registerCustomer(UserRequest request) throws EmailException, PasswordException;

    CustomerResponse getCustomer(String id) throws UserNotFoundException;

    Response deleteCustomer(String id) throws UserNotFoundException;
    Response updateCustomer(UserRequest request) throws UserNotFoundException;

    List<CustomerResponse> getAllCustomers();
    void buildCustomerData(UserRequest request, User user);

    Response deleteAllCustomers();


    Response orderItems(OrderRequest request) throws UserNotFoundException, ProductNotFoundException, OutOfStockException, InvalidQuantityException;

    CustomerResponse getCustomerByEmail(String email) throws UserNotFoundException;

    Response makeAdmin(String id) throws UserNotFoundException;

    Cart getCustomerCart(String id) throws UserNotFoundException;
}
