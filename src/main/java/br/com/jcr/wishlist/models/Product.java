package br.com.jcr.wishlist.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "product")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private String id;
    private String description;
}
