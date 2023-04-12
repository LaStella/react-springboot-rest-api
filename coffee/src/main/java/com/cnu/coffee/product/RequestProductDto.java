package com.cnu.coffee.product;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class RequestProductDto {
    private String productId;
    @NotNull(message = "Product Name cannot be null")
    private String name;
    @NotNull(message = "Category cannot be null")
    private Category category;
    @NotNull(message = "Price cannot be null")
    private long price;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
