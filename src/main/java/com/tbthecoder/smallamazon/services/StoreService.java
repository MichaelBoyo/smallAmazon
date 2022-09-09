package com.tbthecoder.smallamazon.services;

import com.tbthecoder.smallamazon.dtos.SellerRequest;
import com.tbthecoder.smallamazon.models.Store;

public interface StoreService {
     void save(Store store) ;


    Store saveStore(SellerRequest sellerRequest);

    boolean existsByName(String storeName);
}
