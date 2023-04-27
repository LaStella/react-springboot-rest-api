package com.cnu.coffee.product;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public ResponseEntity<ResponseProductDto> createProduct(@RequestBody ProductDto productDto) {
        productService.createProduct(productDto);

        ResponseProductDto responseProductDto = new ResponseProductDto();
        BeanUtils.copyProperties(productDto, responseProductDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseProductDto);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ResponseProductDto>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductByAll());
    }

    @RequestMapping(value = "/search")
    public ResponseEntity<List<ResponseProductDto>> searchProduct(@RequestParam("name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductByName(name));
    }
}
