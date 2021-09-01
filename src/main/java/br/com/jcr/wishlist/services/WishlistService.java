package br.com.jcr.wishlist.services;

import br.com.jcr.wishlist.exceptions.MaximumProductLimitException;
import br.com.jcr.wishlist.models.Product;
import br.com.jcr.wishlist.models.Wishlist;
import br.com.jcr.wishlist.templates.WishlistTemplate;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {


    @Autowired
    private WishlistTemplate wishlistTemplate;

    public void insertCustomerWishlist(Wishlist wishlist) throws MaximumProductLimitException {
        if(wishlist.getProducts().size() > 20)
            throw new MaximumProductLimitException("Limite máximo de 20 produtos na lista de desejos atingida!");

        wishlistTemplate.insertCustomerWishlist(wishlist);
    }

    public Wishlist findCustomerWishlist(String customerId){
        Wishlist wishlist = wishlistTemplate.findCustomerWishlist(customerId);
        return  wishlist;
    }

    public Wishlist findProductInCustomerWishlist(String customerId, String productId){
        return  wishlistTemplate.findProductInCustomerWishlist(customerId, productId);
    }

    public UpdateResult removeProductInCustomerWishlist(String customerId, String productId){
        return  wishlistTemplate.removeProductInCustomerWishlist(customerId, productId);
    }

    public DeleteResult deleteCustomerWishlist(String customerId){
        return  wishlistTemplate.deleteCustomerWishlist(customerId);
    }

    public UpdateResult insertProductInCustomerWishlist(String customerId, Product product) throws MaximumProductLimitException {
        Wishlist wishlist = this.findCustomerWishlist(customerId);
        if(wishlist.getProducts().size() >= 20)
            throw new MaximumProductLimitException("Limite máximo de 20 produtos na lista de desejos atingida!");

        return  wishlistTemplate.insertProductInCustomerWishlist(customerId, product);
    }
}
