package com.cnu.coffee;

import lombok.Data;

@Data
public class ResponseProductDto {
    private String name;
    private Category category;
    private long price;
    private String description;
}
