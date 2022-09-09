package com.tbthecoder.smallamazon.services;

import com.tbthecoder.smallamazon.dtos.SellerRequest;
import com.tbthecoder.smallamazon.models.Store;
import com.tbthecoder.smallamazon.repositories.StoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@AllArgsConstructor
@Service
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;

    @Override
    public void save(Store store) {
        storeRepository.save(store);
    }

    @Override
    public Store saveStore(SellerRequest sellerRequest) {
        return storeRepository.save(Store.builder().description(sellerRequest.storeDescription())
                .products(new HashSet<>())
                .image(sellerRequest.storeImage()).location(sellerRequest.storeAddress())
                .name(sellerRequest.storeName()).build());
    }

    @Override
    public boolean existsByName(String storeName) {
        return storeRepository.existsByName(storeName);
    }
}
