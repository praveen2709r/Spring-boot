package com.Ecommerce.ProductService.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    @NotBlank(message = "Product name is required")
    private String productName;
    @Size(max = 500, message = "Description cannot exceed more than 500 characters")
    private String productDescription;
    @Positive(message = "Price cannot be negative")
    @NotNull(message = "Product price is required")
    private Integer productPrice;
    @NotBlank(message = "Category is required")
    private String category;
    @NotNull(message = "Availability is required")
    private Double availability;
}
