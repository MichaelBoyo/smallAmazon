package com.tbthecoder.smallamazon.repositories;

import com.tbthecoder.smallamazon.models.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface ItemRepository extends MongoRepository<Item,String> {
}
