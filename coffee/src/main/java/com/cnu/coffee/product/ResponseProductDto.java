package com.cnu.coffee.product;

import lombok.Data;

@Data
public class ResponseProductDto {
    private String productId;
    private String name;
    private Category category;
    private Integer price;
    private String description;
}
