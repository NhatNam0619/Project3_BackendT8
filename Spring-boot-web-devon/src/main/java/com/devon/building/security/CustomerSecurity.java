package com.devon.building.security;

import com.devon.building.entity.Customer;
import com.devon.building.entity.User;
import com.devon.building.repository.CustomerRepository;
import com.devon.building.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("customerSecurity")
@AllArgsConstructor
public class CustomerSecurity {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public boolean isOwner(Long customerId) {
        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        Customer customer = customerRepository.findById(customerId)
                .orElse(null);
        if (customer == null || customer.getUsers() == null) {
            return false;
        }
        return customer.getUsers().contains(user);
    }
}
