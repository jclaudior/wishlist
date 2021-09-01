package br.com.jcr.wishlist.resources;

import br.com.jcr.wishlist.models.Product;
import br.com.jcr.wishlist.models.ResultData;
import br.com.jcr.wishlist.models.Wishlist;
import br.com.jcr.wishlist.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/wishlist")
public class WishlistResources {
    @Autowired
    private WishlistService wishlistService;

    @PostMapping
    public ResponseEntity<ResultData> insertWishlist(@RequestBody Wishlist wishlist){
        try {
            wishlistService.insertCustomerWishlist(wishlist);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResultData(HttpStatus.CREATED.value(), "Produto inserido com sucesso há lista de desejos!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResultData(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }

    }

    @PostMapping("/customerId/{customerId}")
    public ResponseEntity<ResultData> insertProductInCustomerWishlist(@PathVariable String customerId, @RequestBody Product product){
        try {
            wishlistService.insertProductInCustomerWishlist(customerId,product);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResultData(HttpStatus.CREATED.value(), "Produto inserido com sucesso há lista de desejos!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResultData(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }

    }

    @GetMapping("/customerId/{customerId}")
    public ResponseEntity<ResultData<Wishlist>> findCustomerWishlist(@PathVariable String customerId){
        try {
            Wishlist wishlist = wishlistService.findCustomerWishlist(customerId);
            return ResponseEntity.status(HttpStatus.OK).body(new ResultData(HttpStatus.OK.value(), "",wishlist));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResultData(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }

    @GetMapping("/customerId/{customerId}/productId/{productId}")
    public ResponseEntity<ResultData<Wishlist>> findProductInCustomerWishlist(@PathVariable String customerId, @PathVariable String productId){
        try {
            Wishlist wishlist = wishlistService.findProductInCustomerWishlist(customerId, productId);
            return ResponseEntity.status(HttpStatus.OK).body(new ResultData(HttpStatus.OK.value(), "",wishlist));

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResultData(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }

    @DeleteMapping("/customerId/{customerId}/productId/{productId}")
    public ResponseEntity<ResultData> removeProductInCustomerWishlist(@PathVariable String customerId, @PathVariable String productId){
        try {
            wishlistService.removeProductInCustomerWishlist(customerId,productId);
            return ResponseEntity.status(HttpStatus.OK).body(new ResultData(HttpStatus.OK.value(), "Produto removido com sucesso há lista de desejos!"));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResultData(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }

    @DeleteMapping("/customerId/{customerId}")
    public ResponseEntity<ResultData> deleteCustomerWishlist(@PathVariable String customerId){
        try {
            wishlistService.deleteCustomerWishlist(customerId);
            return ResponseEntity.status(HttpStatus.OK).body(new ResultData(HttpStatus.OK.value(), "Lista de desejos apagada com sucesso!"));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResultData(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }

}
