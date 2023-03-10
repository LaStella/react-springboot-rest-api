package com.example.coffee.controller.api;

import com.example.coffee.model.Category;
import com.example.coffee.model.Product;
import com.example.coffee.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    // 전체 상품 또는 카테고리별 상품을 조회하는 컨트롤러 메소드
    // api/버전/다건의 상품이 반환된다는 것을 알리기 위해 복수형
    @GetMapping("/api/v1/products")
    public List<Product> productList(@RequestParam Optional<Category> category) {
        // 카테고리가 존재하면 카테고리별 상품 리스트를 반환하고 그렇지 않으면 모든 상품리스트를 반환한다.
        return category
                .map(productService::getProductsByCategory)
                .orElse(productService.getAllProducts());
    }
}
