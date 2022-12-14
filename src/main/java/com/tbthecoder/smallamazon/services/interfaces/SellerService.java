package com.tbthecoder.smallamazon.services.interfaces;

import com.tbthecoder.smallamazon.dtos.*;
import com.tbthecoder.smallamazon.exceptions.EmailException;
import com.tbthecoder.smallamazon.exceptions.PasswordException;
import com.tbthecoder.smallamazon.exceptions.StoreNameTakenException;
import com.tbthecoder.smallamazon.exceptions.UserNotFoundException;

import java.util.List;

public interface SellerService {
    RegisterResponse register(SellerRequest sellerRequest) throws StoreNameTakenException, EmailException, PasswordException;
    SellerResponse getSeller(String id) throws UserNotFoundException;
    Response deleteSeller(String id) throws UserNotFoundException;
    List<SellerResponse> getAllSellers();


    Response deleteAllSellers();

    Response addProduct(ProductRequest productRequest) throws UserNotFoundException;

    SellerResponse getSellerByEmail(String personalEmail);

    void tearDown();
}
