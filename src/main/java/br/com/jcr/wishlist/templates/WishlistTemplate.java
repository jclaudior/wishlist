package br.com.jcr.wishlist.templates;

import br.com.jcr.wishlist.models.Product;
import br.com.jcr.wishlist.models.Wishlist;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class WishlistTemplate {
    @Autowired
    MongoTemplate mongoTemplate;

    public Wishlist insertCustomerWishlist(Wishlist wishlist){
       return mongoTemplate.insert(wishlist);
    }

    public Wishlist findProductInCustomerWishlist(String customerId, String productId){
        Query query = new Query();
        query.addCriteria(Criteria.where("customerId").is(customerId));
        query.fields().elemMatch("products",Criteria.where("_id").is(productId));

        Wishlist wishlist = mongoTemplate.findOne(query, Wishlist.class);
        return wishlist;
    }

    public Wishlist findCustomerWishlist(String customerId){
        Query query = new Query();
        query.addCriteria(Criteria.where("customerId").is(customerId));

        Wishlist wishlist = mongoTemplate.findOne(query, Wishlist.class);
        return wishlist;
    }

    public UpdateResult removeProductInCustomerWishlist(String customerId, String productId){
        Query removeQuery = new Query();
        removeQuery.addCriteria(Criteria.where("customerId").is(customerId));
        removeQuery.fields().elemMatch("products",Criteria.where("_id").is(productId));

        UpdateResult updateResult = mongoTemplate.upsert(
                removeQuery,
                new Update().pull("products", Query.query(Criteria.where("_id").is(productId))),
                Wishlist.class);
        return updateResult;
    }

    public UpdateResult insertProductInCustomerWishlist(String customerId, Product product){
        Query query = new Query();
        query.addCriteria(Criteria.where("customerId").is(customerId));
        return mongoTemplate.updateFirst(query, new Update().push("products", product), Wishlist.class);
    }
}
