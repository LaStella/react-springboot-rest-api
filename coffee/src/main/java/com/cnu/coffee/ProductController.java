package com.cnu.coffee;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<ResponseProduct> createProduct(@RequestBody RequestProduct product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        productService.createProduct(productDto);

        ResponseProduct responseProduct = new ResponseProduct();
        BeanUtils.copyProperties(productDto, responseProduct);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseProduct);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ResponseProduct>> getAllProducts() {
        Iterable<Product> productList = productService.getProductByAll();

        List<ResponseProduct> result = new ArrayList<>();

        productList.forEach(v -> {
            ResponseProduct responseProduct = new ResponseProduct();
            BeanUtils.copyProperties(v, responseProduct);
            result.add(responseProduct);
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @RequestMapping(value = "/search")
    public String search(@RequestParam("id") Long idx) {
        return productService.getProductById(idx).getProductName();
    }
}
