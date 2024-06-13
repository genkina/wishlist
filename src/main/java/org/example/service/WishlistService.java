package org.example.service;

import lombok.Getter;
import org.example.model.Customer;
import org.example.model.Product;
import org.example.repository.CustomerRepository;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@Getter
public class WishlistService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    public void addProductToWishlist(Long customerId, Long productId) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        Optional<Product> productOpt = productRepository.findById(productId);

        if (customerOpt.isPresent() && productOpt.isPresent()) {
            Customer customer = customerOpt.get();
            Product product = productOpt.get();
            customer.getWishlist().add(product);
            customerRepository.save(customer);
        }
    }

    public void removeProductFromWishlist(Long customerId, Long productId) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        Optional<Product> productOpt = productRepository.findById(productId);

        if (customerOpt.isPresent() && productOpt.isPresent()) {
            Customer customer = customerOpt.get();
            Product product = productOpt.get();
            customer.getWishlist().remove(product);
            customerRepository.save(customer);
        }
    }

    public Set<Product> getWishlist(Long customerId) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        return customerOpt.map(Customer::getWishlist).orElse(null);
    }

    public boolean isProductInWishlist(Long customerId, Long productId) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            return customer.getWishlist().stream().anyMatch(product -> product.getId().equals(productId));
        }
        return false;
    }
}
