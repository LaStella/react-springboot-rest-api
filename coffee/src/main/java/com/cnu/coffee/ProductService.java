package com.cnu.coffee;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {
    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(ProductDto productDto) {
        productDto.setProductId(UUID.randomUUID().toString());

        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);

        return productRepository.save(product);
    }

    public Iterable<Product> getProductByAll() {
        return productRepository.findAll();
    }

    public Product getProductById(Long idx) {
        return productRepository.findById(idx).get();
    }

    public Iterable<Product> getProductByName(String name) {
        return productRepository.findByNameContaining(name);
    }
}
