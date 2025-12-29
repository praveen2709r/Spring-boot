package com.Ecommerce.ProductService.Service;

import com.Ecommerce.ProductService.DTO.ProductRequest;
import com.Ecommerce.ProductService.DTO.ProductResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;

public interface ProductService {
    ProductResponse create(ProductRequest productRequest);

    List<ProductResponse> findAll();

    ProductResponse findByProductId(Long productId);

    ProductResponse updateByProductId(ProductRequest productRequest, Long productId);

    void deleteById(Long productId);

    List<ProductResponse> searchProduct(String name, String category, Double minPrice, Double maxPrice, Boolean availability);

    Page<ProductResponse> getAllProducts(Pageable pageable);

    @Nullable Page<ProductResponse> searchProductPage(String name, String category, Double minPrice, Double maxPrice, Boolean availability, Pageable pageable);
}
