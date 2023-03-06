package com.example.gccoffee.repository;

import com.example.gccoffee.model.Category;
import com.example.gccoffee.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {

    // 모든 상품 반환
    List<Product> findAll();

    Product insert(Product product);

    Product update(Product product);

    // 상품을 id와 이름으로 각각 검색
    Optional<Product> findById(UUID productId);

    Optional<Product> findByName(String productName);

    // 카테고리에 대한 상품을 다건으로 검색
    List<Product> findByCategory(Category category);

    void deleteAll();
}
