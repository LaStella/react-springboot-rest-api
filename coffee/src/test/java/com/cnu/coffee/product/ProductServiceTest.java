package com.cnu.coffee.product;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Test
    @DisplayName("상품 생성 테스트")
    public void testCreateProduct() {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(UUID.randomUUID().toString());
        productDto.setName("aaa");
        productDto.setCategory(Category.COFFEBEAN_BEAN_PACKAGE);
        productDto.setPrice(1000L);
        productDto.setDescription("bbb");

        Product savedProduct = productService.createProduct(productDto);

        log.info("created at : {}", savedProduct.getCreatedAt());
        assertEquals(productDto.getName(), savedProduct.getName());
    }
}