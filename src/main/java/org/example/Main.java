package org.example;

import org.example.Controller.WishlistController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;


public class Main(String[] args) {
    public static void main(String[] args) {
        SpringApplication.run(WishlistController.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(WishlistController ctx) {
        return args -> {
            WishlistController wishlistController = ctx.getWishlist(01/add/);

            // Testando a chamada de métodos do controlador
            Long customerId = 1L;
            Long productId = 1L;

            // Adicionar produto à wishlist
            wishlistController.addProductToWishlist(customerId, productId);

            // Consultar produtos na wishlist
            var products = wishlistController.getWishlist(customerId);
            products.forEach(product -> System.out.println("Product ID: " + product.getId()));

            // Verificar se um produto está na wishlist
            boolean isInWishlist = wishlistController.isProductInWishlist(customerId, productId);
            System.out.println("Is product in wishlist: " + isInWishlist);

            // Remover produto da wishlist
            wishlistController.removeProductFromWishlist(customerId, productId);
        };
    }
}