package com.cnu.coffee;

import lombok.Data;

@Data
public class ResponseProduct {
    private String name;
    private Category category;
    private long price;
    private String description;
}
