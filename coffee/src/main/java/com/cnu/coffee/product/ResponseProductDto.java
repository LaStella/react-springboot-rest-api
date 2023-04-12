package com.cnu.coffee.product;

import lombok.Data;

@Data
public class ResponseProductDto {
    private String name;
    private Category category;
    private long price;
    private String description;
}
