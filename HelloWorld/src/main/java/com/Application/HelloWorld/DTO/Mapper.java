package com.Application.HelloWorld.DTO;

import com.Application.HelloWorld.Entity.Product;

public class Mapper {
    public static ProductResponseDTO getProductResponseDTO(Product product){
        ProductResponseDTO productResponseDTO=new ProductResponseDTO();
        productResponseDTO.setProductId(product.getProductId());
        productResponseDTO.setProductName(product.getProductName());
        productResponseDTO.setPrice(product.getPrice());
        productResponseDTO.setModelNumber(product.getModelNumber());
        return productResponseDTO;
    }

    public static Product getProductRequestDTO(ProductRequestDTO productRequestDTO) {
        Product product = new Product();
        product.setProductName(productRequestDTO.getProductName());
        product.setAvailability(productRequestDTO.getAvailability());
        product.setModelNumber(productRequestDTO.getModelNumber());
        product.setPrice(productRequestDTO.getPrice());
        return product;
    }
}
