package com.tbthecoder.smallamazon.security;

import com.tbthecoder.smallamazon.models.User;
import com.tbthecoder.smallamazon.repositories.SellerRepository;
import com.tbthecoder.smallamazon.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = customerRepository.findCustomerByEmail(username);
        if (user == null) {
            user = sellerRepository.findSellerByEmail(username);
            if(user == null) throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }


}
