package com.tbthecoder.smallamazon.repositories;

import com.tbthecoder.smallamazon.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface ProductRepository extends MongoRepository<Product,String> {

}
