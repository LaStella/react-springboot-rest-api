package com.cnu.coffee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/save")
    public String save(@RequestParam("name") String name, @RequestParam("category") Category category,@RequestParam("price") long price) {
        productService.createProduct(name, category, price);
        return "save";
    }

    @RequestMapping(value = "/search")
    public String search(@RequestParam("id") Long idx) {
        return productService.getProductById(idx).getProductName();
    }
}
