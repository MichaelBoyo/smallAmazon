package com.tbthecoder.smallamazon.repositories;

import com.tbthecoder.smallamazon.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer,String> {
    boolean existsByEmail(String email);
    Customer findCustomerByEmail(String email);
}
