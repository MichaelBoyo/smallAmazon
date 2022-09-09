package com.tbthecoder.smallamazon.services;

import com.tbthecoder.smallamazon.dtos.Response;
import com.tbthecoder.smallamazon.dtos.SellerRegisterResponse;
import com.tbthecoder.smallamazon.dtos.SellerRequest;
import com.tbthecoder.smallamazon.dtos.SellerResponse;
import com.tbthecoder.smallamazon.models.Roles;
import com.tbthecoder.smallamazon.models.Seller;
import com.tbthecoder.smallamazon.repositories.SellerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;

    @Override
    public SellerRegisterResponse register(SellerRequest sellerRequest) {
        Seller seller = Seller.builder()
                .storeAddress(sellerRequest.storeAddress())
                .storeDescription(sellerRequest.storeDescription())
                .storeEmail(sellerRequest.storeEmail())
                .storeImage(sellerRequest.storeImage())
                .storeName(sellerRequest.storeName())
                .storePhoneNumber(sellerRequest.storePhoneNumber())

                .build();

        seller.setPassword(sellerRequest.password());
        seller.setFirstName(sellerRequest.firstName());
        seller.setLastName(sellerRequest.lastName());
        seller.setEmail(sellerRequest.email());
        seller.setRoles(Set.of(Roles.SELLER));
        sellerRepository.save(seller);
        return null;
    }

    @Override
    public SellerResponse getSeller(String id) {
        return null;
    }

    @Override
    public Response deleteSeller(String id) {
        return null;
    }

    @Override
    public List<SellerResponse> getAllSellers() {
        return null;
    }
}
