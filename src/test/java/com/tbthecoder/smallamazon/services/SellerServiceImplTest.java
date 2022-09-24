package com.tbthecoder.smallamazon.services;

import com.tbthecoder.smallamazon.dtos.ProductRequest;
import com.tbthecoder.smallamazon.dtos.RegisterResponse;
import com.tbthecoder.smallamazon.dtos.SellerRequest;
import com.tbthecoder.smallamazon.dtos.Status;
import com.tbthecoder.smallamazon.exceptions.UserNotFoundException;
import com.tbthecoder.smallamazon.services.interfaces.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class SellerServiceImplTest {
    @Autowired
    private SellerService sellerService;
    private RegisterResponse res;

    @BeforeEach
    void setUp() throws Exception{
        res = sellerService.register(new SellerRequest("08013297538", "storeEmailTest",
                "12345", "personalEmail", "storeImageTest", "storeAddressTest", "storeNameTest",
                "storeDescTest", "test-FirstName", "test-LastName","re-psw"));
    }

    @AfterEach
    void tearDown() {
        sellerService.deleteAllSellers();
        sellerService.tearDown();
    }

    @Test
    void register() {
    assertEquals(res.status(), Status.SUCCESS);
    assertNotEquals(Collections.emptyList(),sellerService.getAllSellers());
    }

    @Test
    void getSeller() {
        var seller = sellerService.getSellerByEmail("personalEmail");
        assertNotNull(seller);
        assertEquals(seller.firstName(),"test-FirstName");
    }

    @Test
    void deleteSeller() throws UserNotFoundException {
        var seller = sellerService.getSellerByEmail("personalEmail");
        sellerService.deleteSeller(seller.id());

        assertEquals(Collections.emptyList(),sellerService.getAllSellers());
    }

    @Test
    void getAllSellers() {
        assertEquals(1,sellerService.getAllSellers().size());
    }

    @Test
    void deleteAllSellers() {
        sellerService.deleteAllSellers();
        assertEquals(Collections.emptyList(),sellerService.getAllSellers());
    }


    @Test
    void addProduct() throws UserNotFoundException {
        var seller = sellerService.getSellerByEmail("personalEmail");
        sellerService.addProduct(new ProductRequest(seller.id(),"sanndal","fine sandal",
                65.6,45,"oka","lol"));
        seller = sellerService.getSellerByEmail("personalEmail");

        assertEquals(seller.store().getProducts().size(),1);

    }
}