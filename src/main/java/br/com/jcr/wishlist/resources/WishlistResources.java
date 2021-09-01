package br.com.jcr.wishlist.resources;

import br.com.jcr.wishlist.models.Product;
import br.com.jcr.wishlist.models.Wishlist;
import br.com.jcr.wishlist.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/wishlist")
public class WishlistResources {
    @Autowired
    private WishlistService wishlistService;

    @PostMapping
    public ResponseEntity<?> insertWishlist(@RequestBody Wishlist wishlist){
        wishlistService.insertCustomerWishlist(wishlist);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/customerId/{customerId}")
    public ResponseEntity<?> insertProductInCustomerWishlist(@PathVariable String customerId, @RequestBody Product product){
        wishlistService.insertProductInCustomerWishlist(customerId,product);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/customerId/{customerId}")
    public ResponseEntity<?> findCustomerWishlist(@PathVariable String customerId){
        return ResponseEntity.ok().body(wishlistService.findCustomerWishlist(customerId));
    }

    @GetMapping("/customerId/{customerId}/productId/{productId}")
    public ResponseEntity<?> findProductInCustomerWishlist(@PathVariable String customerId, @PathVariable String productId){
        return ResponseEntity.ok().body(wishlistService.findProductInCustomerWishlist(customerId,productId));
    }

    @DeleteMapping("/customerId/{customerId}/productId/{productId}")
    public ResponseEntity<?> removeProductInCustomerWishlist(@PathVariable String customerId, @PathVariable String productId){
        return ResponseEntity.ok().body(wishlistService.removeProductInCustomerWishlist(customerId,productId));
    }

}
