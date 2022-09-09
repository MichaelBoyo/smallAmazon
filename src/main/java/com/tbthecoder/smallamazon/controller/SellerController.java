package com.tbthecoder.smallamazon.controller;

import com.tbthecoder.smallamazon.dtos.Response;
import com.tbthecoder.smallamazon.dtos.SellerRegisterResponse;
import com.tbthecoder.smallamazon.dtos.SellerRequest;
import com.tbthecoder.smallamazon.dtos.SellerResponse;
import com.tbthecoder.smallamazon.services.SellerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/seller")
@Slf4j
@AllArgsConstructor
public class SellerController {
    private final SellerService sellerService;

    @PostMapping
    public SellerRegisterResponse register(@RequestBody SellerRequest sellerRequest) {
        log.info("registering seller");
        return sellerService.register(sellerRequest);
    }

    @GetMapping("/{id}")
    public  SellerResponse getSeller(@PathVariable String id) {
        log.info("getting seller {}", id);
        return sellerService.getSeller(id);
    }
    @DeleteMapping("/{id}")
    public Response deleteSeller(@PathVariable String id){
        return sellerService.deleteSeller(id);
    }

    @GetMapping("/all")
    public List<SellerResponse> getAllSellers() {
        log.info("getting all sellers");
        return sellerService.getAllSellers();
    }
}
