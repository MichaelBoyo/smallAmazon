package com.tbthecoder.smallamazon.repositories;

import com.tbthecoder.smallamazon.models.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {
}
