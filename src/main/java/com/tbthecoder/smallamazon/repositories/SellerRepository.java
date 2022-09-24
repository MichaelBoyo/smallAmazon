package com.tbthecoder.smallamazon.repositories;

import com.tbthecoder.smallamazon.models.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface SellerRepository extends MongoRepository<Seller, String> {
    boolean existsByEmail(String email);
    Seller findSellerByEmail(String email);
}
