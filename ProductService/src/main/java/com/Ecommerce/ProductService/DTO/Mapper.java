package com.Ecommerce.ProductService.DTO;

import com.Ecommerce.ProductService.Entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public Product mapToProduct(ProductRequest productRequest) {
        Product product=new Product();
        product.setProductName(productRequest.getProductName());
        product.setProductDescription(productRequest.getProductDescription());
        product.setProductPrice(productRequest.getProductPrice());
        product.setAvailability(productRequest.getAvailability());
        product.setCategory(productRequest.getCategory());
        return product;
    }

    public ProductResponse mapToProductResponse(Product product) {
        ProductResponse productResponse=new ProductResponse();
        productResponse.setProductName(product.getProductName());
        productResponse.setProductPrice(product.getProductPrice());
        productResponse.setProductDescription(product.getProductDescription());
        productResponse.setCategory(product.getCategory());
        return productResponse;
    }


}
