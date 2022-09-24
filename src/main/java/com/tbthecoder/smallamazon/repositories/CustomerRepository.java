package com.tbthecoder.smallamazon.repositories;

import com.tbthecoder.smallamazon.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.Optional;


public interface CustomerRepository extends MongoRepository<Customer,String> {
    boolean existsByEmail(String email);
    Customer findCustomerByEmail(String email);

    Optional<Customer> findByEmail(String email);
}
