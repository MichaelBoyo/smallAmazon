package com.tbthecoder.smallamazon.repositories;

import com.tbthecoder.smallamazon.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {

}
