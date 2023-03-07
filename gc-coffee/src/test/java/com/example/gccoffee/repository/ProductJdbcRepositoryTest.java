package com.example.gccoffee.repository;

import com.example.gccoffee.model.Category;
import com.example.gccoffee.model.Product;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

// SpringBootApplication 을 찾아준다. 어플리케이션 안에서 SpringBootConfiguration을 읽어 bean 설정을 읽는다.
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductJdbcRepositoryTest {

    @Autowired
    ProductRepository repository;

    private final Product newProduct = new Product(UUID.randomUUID(), "new-product", Category.COFFEBEAN_BEAN_PACKAGE, 1000L);

    @BeforeAll
    void setup() {
        repository.deleteAll();
    }

    @Test
    @Order(1)
    @DisplayName("상품을 추가할 수 있다.")
    void testInsert() {
        repository.insert(newProduct);
        var all = repository.findAll();
        assertThat(all.isEmpty(), is(false));
    }
}
