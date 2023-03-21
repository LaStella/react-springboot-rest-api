package com.cnu.coffee;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class ProductRepository {

    Map<Long, Product> db = new HashMap<>();

    public Product insert(Product product) {
        db.put(product.getProductId(), product);
        return product;
    }

    public Optional<Product> findById(Long id) {
        try {
            return Optional.of(db.get(id));
        } catch (NullPointerException e) {
            return Optional.empty();
        }
    }
}
