package com.tbthecoder.smallamazon.repositories;

import com.tbthecoder.smallamazon.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
    boolean existsByEmail(String email);
}
