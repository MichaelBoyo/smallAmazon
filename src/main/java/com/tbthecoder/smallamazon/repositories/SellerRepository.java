package com.tbthecoder.smallamazon.repositories;

import com.tbthecoder.smallamazon.models.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SellerRepository extends MongoRepository<Seller, String> {
    boolean existsByEmail(String email);
}
