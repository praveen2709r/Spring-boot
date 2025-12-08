package com.Application.HelloWorld.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {
    @NotBlank(message="Product name cannot be empty")
    private String productName;
    @NotNull(message="Product price cannot be empty")
    private Double price;
    @NotBlank(message="Model number cannot be empty")
    private String modelNumber;
    @NotNull(message="Product availability required")
    private Long availability;
}
