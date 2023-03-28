package com.cnu.coffee;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestProduct {
    @NotNull(message = "Product Name cannot be null")
    private String productName;
    @NotNull(message = "Category cannot be null")
    private Category category;
    @NotNull(message = "Price cannot be null")
    private long price;
    private String description;
}
