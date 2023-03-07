package com.example.gccoffee.controller;

import com.example.gccoffee.model.Category;

// DTO
public record CreateProductRequest(String productName, Category category, long price, String description) {
}
