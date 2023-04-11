package com.cnu.coffee;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(RequestProductDto requestProductDto) {
        requestProductDto.setProductId(UUID.randomUUID().toString());

        Product product = new Product();
        BeanUtils.copyProperties(requestProductDto, product);

        return productRepository.save(product);
    }

    public List<ResponseProductDto> getProductByAll() {
        List<Product> productList = productRepository.findAll();

        List<ResponseProductDto> result = new ArrayList<>();
        productList.forEach(v -> {
            ResponseProductDto responseProductDto = new ResponseProductDto();
            BeanUtils.copyProperties(v, responseProductDto);
            result.add(responseProductDto);
        });

        return  result;
    }

    public ResponseProductDto getProductById(String productId) {
        Product product = productRepository.findByProductId(productId);

        ResponseProductDto responseProductDto = new ResponseProductDto();
        BeanUtils.copyProperties(product, responseProductDto);

        return responseProductDto;
    }

    public List<ResponseProductDto> getProductByName(String name) {
        List<Product> productList = productRepository.findByNameContaining(name);

        List<ResponseProductDto> result = new ArrayList<>();
        productList.forEach(v -> {
            ResponseProductDto responseProductDto = new ResponseProductDto();
            BeanUtils.copyProperties(v, responseProductDto);
            result.add(responseProductDto);
        });

        return result;
    }
}
