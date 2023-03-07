package com.example.gccoffee.controller;

import com.example.gccoffee.model.Product;
import com.example.gccoffee.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


// Rest API 를 만드는 컨트롤러가 아니다. (RestController를 사용하지 않았음)
// 관리자가 웹페이지에 접속하기위한 뷰를 반환해주는 컨트롤러
@Controller
public class ProductController {

    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // /products에 접속하게되면 뷰를 반환
    @GetMapping("/products")
    public String productsPage(Model model) {
        var products = productService.getAllProducts();
        model.addAttribute("products", products);
        // product-list라는 템플릿을 찾게된다.
        return "product-list";
    }
}