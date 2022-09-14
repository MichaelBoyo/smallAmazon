package com.tbthecoder.smallamazon.services;

import com.tbthecoder.smallamazon.controller.utils.Validator;
import com.tbthecoder.smallamazon.dtos.*;
import com.tbthecoder.smallamazon.exceptions.*;
import com.tbthecoder.smallamazon.models.*;
import com.tbthecoder.smallamazon.repositories.CustomerRepository;
import com.tbthecoder.smallamazon.services.interfaces.CartService;
import com.tbthecoder.smallamazon.services.interfaces.CustomerService;
import com.tbthecoder.smallamazon.services.interfaces.ItemService;
import com.tbthecoder.smallamazon.services.interfaces.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.tbthecoder.smallamazon.controller.utils.Validator.*;
import static com.tbthecoder.smallamazon.dtos.Status.SUCCESS;
import static com.tbthecoder.smallamazon.models.Roles.*;

@AllArgsConstructor
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    final PasswordEncoder passwordEncoder;
    private final CartService cartService;
    private final ItemService itemService;
    private final ProductService productService;

    @Override
    public RegisterResponse registerCustomer(RegisterRequest request) throws EmailExistsException, PasswordMisMatchException {
        if (customerRepository.existsByEmail(request.email())) throw new EmailExistsException("email used");
        validatePassword(request);
        validateEmail(request);
        Customer customer = Customer.builder()
                .cart(cartService.saveCart(request))
                .build();
        buildCustomerData(request, customer);
        customerRepository.save(customer);
        return new RegisterResponse(SUCCESS, "Customer registered successfully");
    }

    @Override
    public void buildCustomerData(RegisterRequest request, User customer) {
        customer.setPassword(passwordEncoder.encode(request.password()));
        customer.setFirstName(request.firstname());
        customer.setLastName(request.lastname());
        customer.setEmail(request.email());
        customer.setPhoneNumber(request.phoneNumber());
        customer.setRoles(Set.of(CUSTOMER));

    }


    private Customer get(String id) throws UserNotFoundException {
        return customerRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user not found"));
    }

    @Override
    public CustomerResponse getCustomer(String id) throws UserNotFoundException {
        return get(id).toCustomerResponse();
    }

    @Override
    public Response deleteCustomer(String id) throws UserNotFoundException {
        customerRepository.delete(get(id));
        return new Response(SUCCESS, "Customer Deleted Successfully");
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll().stream().map(Customer::toCustomerResponse).toList();
    }

    @Override
    public Response deleteAllCustomers() {
        customerRepository.deleteAll();
        return new Response(SUCCESS, "All customers Deleted Successfully");
    }

    @Override
    public Response orderItems(OrderRequest request) throws UserNotFoundException, ProductNotFoundException, OutOfStockException, InvalidQuantityException {
        Product product = fetchProduct(request);
        Item item = itemService.saveItem(new ItemDTO(product, request.quantity(),
                request.quantity() * product.getPrice()
        ));
        Customer customer = get(request.customerId());
        Cart cart = customer.getCart();
        cart.getProducts().add(item);
        cartService.save(cart);
        customerRepository.save(customer);
        return new Response(Status.ORDER_SUCCESS, "Item Added to Cart Successfully");
    }

    @Override
    public CustomerResponse geCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email).toCustomerResponse();
    }

    @Override
    public Response makeAdmin(String id) throws UserNotFoundException {
        Customer customer = get(id);
        customer.getRoles().add(Roles.ADMIN);
        customerRepository.save(customer);
        return new Response(SUCCESS, "now an Admin");
    }

    @Override
    public Cart getCustomerCart(String id) throws UserNotFoundException {
        log.info("getting cart for customer {}", id);
        Customer customer = get(id);
        return customer.getCart();
    }

    private Product fetchProduct(OrderRequest request) throws ProductNotFoundException, OutOfStockException, InvalidQuantityException {
        Product product = productService.getProduct(request.productId());
        if (product.getStockAvailable() == 0) throw new OutOfStockException("product Out of Stock");
        if (request.quantity() > product.getStockAvailable())
            throw new InvalidQuantityException("quantity ordered is not available, only " + product.getStockAvailable() + " units available");
        product.setStockAvailable(product.getStockAvailable() - request.quantity());
        productService.save(product);
        return product;
    }
}
