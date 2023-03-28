package com.cnu.coffee;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<ResponseProduct> createUser(@RequestBody RequestProduct product) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ProductDto productDto = mapper.map(product, ProductDto.class);
        productService.createProduct(productDto);

        ResponseProduct responseProduct = mapper.map(productDto, ResponseProduct.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseProduct);
    }

    @RequestMapping(value = "/search")
    public String search(@RequestParam("id") Long idx) {
        return productService.getProductById(idx).getProductName();
    }
}
