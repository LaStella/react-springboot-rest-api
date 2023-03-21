package com.cnu.coffee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    Long id = 1L;

    public Product createProduct(String productName, Category category, long price) {
        var product = new Product(id++, productName, category, price);
        return productRepository.insert(product);
    }

    public Product getProductById(Long idx) {
        return productRepository.findById(idx).get();
    }
}
