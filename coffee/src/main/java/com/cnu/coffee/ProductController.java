package com.cnu.coffee;

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

        return getListResponseEntity(productList);
    }

    @RequestMapping(value = "/search")
    public ResponseEntity<List<ResponseProduct>> searchProduct(@RequestParam("name") String name) {
        Iterable<Product> productList = productService.getProductByName(name);

        return getListResponseEntity(productList);
    }

    private ResponseEntity<List<ResponseProduct>> getListResponseEntity(Iterable<Product> productList) {
        List<ResponseProduct> result = new ArrayList<>();

        productList.forEach(v -> {
            ResponseProduct responseProduct = new ResponseProduct();
            BeanUtils.copyProperties(v, responseProduct);
            result.add(responseProduct);
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
