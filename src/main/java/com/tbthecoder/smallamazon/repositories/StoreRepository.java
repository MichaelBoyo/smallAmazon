package com.tbthecoder.smallamazon.repositories;

import com.tbthecoder.smallamazon.models.Store;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StoreRepository extends MongoRepository<Store,String> {
    boolean existsByName(String name);
}
