package com.tbthecoder.smallamazon.services;

import com.tbthecoder.smallamazon.dtos.Response;
import com.tbthecoder.smallamazon.dtos.SellerRegisterResponse;
import com.tbthecoder.smallamazon.dtos.SellerRequest;
import com.tbthecoder.smallamazon.dtos.SellerResponse;

import java.util.List;

public interface SellerService {
    SellerRegisterResponse register(SellerRequest sellerRequest);
    SellerResponse getSeller(String id);
    Response deleteSeller(String id);
    List<SellerResponse> getAllSellers();


}
