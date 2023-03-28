package com.cnu.coffee;

import lombok.Data;

@Data
public class ResponseProduct {
    private String productName;
    private Category category;
    private long price;
    private String description;
}
