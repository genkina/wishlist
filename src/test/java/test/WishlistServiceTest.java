package test;

import org.example.model.Customer;
import org.example.model.Product;
import org.example.repository.CustomerRepository;
import org.example.repository.ProductRepository;
import org.example.service.WishlistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

public class WishlistServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private WishlistService wishlistService;

    private Customer customer;
    private Product product;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        customer = new Customer();
        customer.setId(1L);
        customer.setWishlist(new HashSet<>());

        product = new Product();
        product.setId(1L);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
    }

    @Test
    public void testAddProductToWishlist() {
        wishlistService.addProductToWishlist(1L, 1L);
        assertTrue(customer.getWishlist().contains(product));
    }

    @Test
    public void testRemoveProductFromWishlist() {
        customer.getWishlist().add(product);
        wishlistService.removeProductFromWishlist(1L, 1L);
        assertFalse(customer.getWishlist().contains(product));
    }

    @Test
    public void testGetWishlist() {
        customer.getWishlist().add(product);
        Set<Product> wishlist = wishlistService.getWishlist(1L);
        assertEquals(1, wishlist.size());
        assertTrue(wishlist.contains(product));
    }

    @Test
    public void testIsProductInWishlist() {
        customer.getWishlist().add(product);
        assertTrue(wishlistService.isProductInWishlist(1L, 1L));
        assertFalse(wishlistService.isProductInWishlist(1L, 2L));
    }
}

