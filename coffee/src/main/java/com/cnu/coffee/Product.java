package com.cnu.coffee;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Product {
    private final UUID productId;
    private String productName;
    private Category category;
    private long price;
    private String description;
    private List<Review> reviews;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 필수정보만 가지는 생성자
    public Product(UUID productId, String productName, Category category, long price) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Product(UUID productId, String productName, Category category, long price, String description, List<Review> reviews, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.description = description;
        this.reviews = reviews;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
