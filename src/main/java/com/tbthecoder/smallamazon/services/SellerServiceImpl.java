package com.tbthecoder.smallamazon.services;

import com.tbthecoder.smallamazon.dtos.*;
import com.tbthecoder.smallamazon.models.Product;
import com.tbthecoder.smallamazon.models.Roles;
import com.tbthecoder.smallamazon.models.Seller;
import com.tbthecoder.smallamazon.models.Store;

import com.tbthecoder.smallamazon.repositories.SellerRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.tbthecoder.smallamazon.dtos.Status.SUCCESS;

@Service
@AllArgsConstructor
@Slf4j
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;
    private final ProductService productService;
    private final StoreService storeService;

    @Override
    public SellerRegisterResponse register(SellerRequest sellerRequest) {
        var FAILURE = checkIfExists(sellerRequest);
        if (FAILURE != null) return FAILURE;
        Seller seller = Seller.builder()
                .store(storeService.saveStore(sellerRequest)).build();
        buildSellerPersonalData(sellerRequest, seller);
        sellerRepository.save(seller);
        return new SellerRegisterResponse(SUCCESS, "Seller Registered successfully");
    }

    private void buildSellerPersonalData(SellerRequest sellerRequest, Seller seller) {
        seller.setPassword(sellerRequest.password());
        seller.setFirstName(sellerRequest.firstName());
        seller.setLastName(sellerRequest.lastName());
        seller.setEmail(sellerRequest.email());
        seller.setPhoneNumber(sellerRequest.phoneNumber());
        seller.setRoles(Set.of(Roles.SELLER));
    }

    private SellerRegisterResponse checkIfExists(SellerRequest sellerRequest) {
        if (sellerRepository.existsByEmail(sellerRequest.email())) {
            return new SellerRegisterResponse(Status.FAILURE, "Email already exists");
        }
        if (storeService.existsByName(sellerRequest.storeName())) {
            return new SellerRegisterResponse(Status.FAILURE, "Store Name already exists");
        }
        return null;
    }

    private Seller get(String id) throws SellerNotFoundException {
        return sellerRepository.findById(id).orElseThrow(() -> new SellerNotFoundException("Seller not found"));
    }

    @Override
    public SellerResponse getSeller(String id) throws SellerNotFoundException {
        return get(id).toSellerResponse();
    }

    @Override
    public Response deleteSeller(String id) {
        return new Response(SUCCESS, "Deleted Successfully");
    }

    @Override
    public List<SellerResponse> getAllSellers() {
        return sellerRepository.findAll().stream()
                .map(seller -> new SellerResponse(seller.getId(), seller.getStore())).toList();
    }

    @Override
    public Response deleteAllSellers() {
        sellerRepository.deleteAll();
        return new Response(SUCCESS, "Deleted Successfully");
    }

    @Override
    public Response addProduct(AddProductRequest addProductRequest) throws SellerNotFoundException {
        Seller seller = get(addProductRequest.sellerId());
        Store store = seller.getStore();
        store.getProducts().add(product(addProductRequest));
        storeService.save(store);
        sellerRepository.save(seller);
        return new Response(SUCCESS, "Product Added Successfully");
    }

    private Product product(AddProductRequest addProductRequest) {
        return productService.saveProduct(addProductRequest);
    }
}
