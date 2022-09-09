package com.tbthecoder.smallamazon.services;

import com.tbthecoder.smallamazon.dtos.*;

import java.util.List;

public interface SellerService {
    SellerRegisterResponse register(SellerRequest sellerRequest);
    SellerResponse getSeller(String id) throws SellerNotFoundException;
    Response deleteSeller(String id);
    List<SellerResponse> getAllSellers();


    Response deleteAllSellers();

    Response addProduct(AddProductRequest addProductRequest) throws SellerNotFoundException;
}
