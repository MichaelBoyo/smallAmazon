package com.tbthecoder.smallamazon.models;


import com.tbthecoder.smallamazon.dtos.CustomerResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document
@Getter
@Setter
public class Customer extends User {
    @Id
    private String customerId;
    @DBRef
    private Cart cart;

    public CustomerResponse toCustomerResponse() {
        return new CustomerResponse(customerId, this.getFirstName(),
                this.getLastName(), this.getEmail(),
                this.getPhoneNumber(),cart);
    }
}
