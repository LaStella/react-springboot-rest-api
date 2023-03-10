package com.example.coffee.controller;

import com.example.coffee.model.Category;

// DTO
public record CreateProductRequest(String productName, Category category, long price, String description) {
}
