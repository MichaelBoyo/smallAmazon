package com.tbthecoder.smallamazon.services;

import com.tbthecoder.smallamazon.dtos.*;
import com.tbthecoder.smallamazon.exceptions.EmailExistsException;
import com.tbthecoder.smallamazon.exceptions.StoreNameTakenException;
import com.tbthecoder.smallamazon.exceptions.UserNotFoundException;
import com.tbthecoder.smallamazon.models.Product;
import com.tbthecoder.smallamazon.models.Roles;
import com.tbthecoder.smallamazon.models.Seller;
import com.tbthecoder.smallamazon.models.Store;

import com.tbthecoder.smallamazon.repositories.SellerRepository;

import com.tbthecoder.smallamazon.services.interfaces.ProductService;
import com.tbthecoder.smallamazon.services.interfaces.SellerService;
import com.tbthecoder.smallamazon.services.interfaces.StoreService;
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
    public RegisterResponse register(SellerRequest sellerRequest) throws StoreNameTakenException, EmailExistsException {
        checkIfExists(sellerRequest);
        Seller seller = Seller.builder()
                .store(storeService.saveStore(sellerRequest)).build();
        buildSellerPersonalData(sellerRequest, seller);
        sellerRepository.save(seller);
        return new RegisterResponse(SUCCESS, "Seller Registered successfully");
    }

    private void buildSellerPersonalData(SellerRequest sellerRequest, Seller seller) {
        seller.setPassword(sellerRequest.password());
        seller.setFirstName(sellerRequest.firstName());
        seller.setLastName(sellerRequest.lastName());
        seller.setEmail(sellerRequest.email());
        seller.setPhoneNumber(sellerRequest.phoneNumber());
        seller.setRoles(Set.of(Roles.SELLER));
    }

    private void checkIfExists(SellerRequest sellerRequest) throws EmailExistsException, StoreNameTakenException {
        if (sellerRepository.existsByEmail(sellerRequest.email())) {
            throw new EmailExistsException("email used");
        }
        if (storeService.existsByName(sellerRequest.storeName())) {
            throw new StoreNameTakenException("Store name taken, use a different one");
        }

    }

    private Seller get(String id) throws UserNotFoundException {
        return sellerRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Seller not found"));
    }

    @Override
    public SellerResponse getSeller(String id) throws UserNotFoundException {
        return get(id).toSellerResponse();
    }

    @Override
    public Response deleteSeller(String id) throws UserNotFoundException {
        sellerRepository.delete(get(id));
        return new Response(SUCCESS, "Deleted Successfully");
    }

    @Override
    public List<SellerResponse> getAllSellers() {
        return sellerRepository.findAll().stream()
                .map(Seller::toSellerResponse).toList();
    }

    @Override
    public Response deleteAllSellers() {
        sellerRepository.deleteAll();
        return new Response(SUCCESS, "Deleted Successfully");
    }

    @Override
    public Response addProduct(AddProductRequest addProductRequest) throws UserNotFoundException {
        Seller seller = get(addProductRequest.sellerId());
        Store store = seller.getStore();
        store.getProducts().add(product(addProductRequest));
        storeService.save(store);
        sellerRepository.save(seller);
        return new Response(SUCCESS, "Product Added Successfully");
    }

    @Override
    public SellerResponse getSellerByEmail(String personalEmail) {
        return sellerRepository.findSellerByEmail(personalEmail).toSellerResponse();
    }

    @Override
    public void tearDown() {
        productService.deleteAll();
        storeService.deleteAll();
    }

    private Product product(AddProductRequest addProductRequest) {
        return productService.saveProduct(addProductRequest);
    }
}
