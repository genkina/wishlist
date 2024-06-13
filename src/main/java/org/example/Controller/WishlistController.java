package org.example.Controller;

import org.example.model.Product;
import org.example.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/{customerId}/add/{productId}")
    public ResponseEntity<Void> addProductToWishlist(@PathVariable Long customerId, @PathVariable Long productId) {
        wishlistService.addProductToWishlist(customerId, productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{customerId}/remove/{productId}")
    public ResponseEntity<Void> removeProductFromWishlist(@PathVariable Long customerId, @PathVariable Long productId) {
        wishlistService.removeProductFromWishlist(customerId, productId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Set<Product>> getWishlist(@PathVariable Class<WishlistController> customerId) {
        Set<Product> wishlist = wishlistService.getWishlist(customerId);
        if (wishlist != null) {
            return ResponseEntity.ok(wishlist);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{customerId}/contains/{productId}")
    public ResponseEntity<Boolean> isProductInWishlist(@PathVariable Long customerId, @PathVariable Long productId) {
        boolean isInWishlist = wishlistService.isProductInWishlist(customerId, productId);
        return ResponseEntity.ok(isInWishlist);
    }
}
