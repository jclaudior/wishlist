package br.com.jcr.wishlist.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "wishlist")
@AllArgsConstructor
@NoArgsConstructor
public class Wishlist {
    @Id
    private String customerId;
    private List<Product> products;
}
