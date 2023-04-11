package com.cnu.coffee;

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
    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName("상품 생성 테스트")
    public void testCreateProduct() {
        RequestProductDto requestProductDto = new RequestProductDto();
        requestProductDto.setProductId(UUID.randomUUID().toString());
        requestProductDto.setName("aaa");
        requestProductDto.setCategory(Category.COFFEBEAN_BEAN_PACKAGE);
        requestProductDto.setPrice(1000L);
        requestProductDto.setDescription("bbb");

        productService.createProduct(requestProductDto);

        Product retrievedProduct = productRepository.findByProductId(requestProductDto.getProductId());
        assertEquals(requestProductDto.getName(), retrievedProduct.getName());
    }
}