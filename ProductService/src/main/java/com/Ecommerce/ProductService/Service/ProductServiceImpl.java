package com.Ecommerce.ProductService.Service;

import com.Ecommerce.ProductService.DTO.Mapper;
import com.Ecommerce.ProductService.DTO.ProductRequest;
import com.Ecommerce.ProductService.DTO.ProductResponse;
import com.Ecommerce.ProductService.Entity.Product;
import com.Ecommerce.ProductService.ExceptionHandling.ProductNotFoundException;
import com.Ecommerce.ProductService.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sound.sampled.Port;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final Mapper mapper;
    private final ProductRepository productRepository;

    public ProductServiceImpl(Mapper mapper, ProductRepository productRepository) {
        this.mapper = mapper;
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public ProductResponse create(ProductRequest productRequest) {
        log.info("Mapping ProductRequest to Product entity");
        Product product = mapper.mapToProduct(productRequest);

        log.info("Saving product to database");
        productRepository.save(product);

        log.info("Product saved successfully with id {}", product.getProductId());
        return mapper.mapToProductResponse(product);
    }


    @Override
    public List<ProductResponse> findAll() {
        return productRepository.findByActiveTrue().stream().map(
                mapper::mapToProductResponse
        ).toList();
    }

    @Override
    public ProductResponse findByProductId(Long productId) {
        Product product=productRepository.findById(productId)
                .orElseThrow(()->new ProductNotFoundException("Product not found with id "+productId));
        return mapper.mapToProductResponse(product);
    }

    @Override
    public ProductResponse updateByProductId(ProductRequest productRequest, Long productId) {
        Product product=productRepository.findById(productId)
                .orElseThrow(()->new ProductNotFoundException("Product not found with id "+productId));
        product.setProductName(productRequest.getProductName());
        product.setProductPrice(productRequest.getProductPrice());
        product.setProductDescription(productRequest.getProductDescription());
        product.setAvailability(productRequest.getAvailability());
        product.setCategory(productRequest.getCategory());
        return mapper.mapToProductResponse(productRepository.save(product));
    }

    @Override
    public void deleteById(Long productId) {
        Product product=productRepository.findById(productId).orElseThrow(()->new ProductNotFoundException("Product not found with id "+productId));
        product.setActive(false);
        productRepository.save(product);
    }

    @Override
    public List<ProductResponse> searchProduct(String name, String category, Double minPrice, Double maxPrice, Boolean availability) {
        List<Product> products=productRepository.searchProducts(name,category
        ,minPrice,maxPrice,availability);
        return products.stream().map(mapper::mapToProductResponse).toList();
    }

    @Override
    public Page<ProductResponse> getAllProducts(Pageable pageable) {
        Page<Product> page=productRepository.findAll(pageable);
        return page.map(mapper::mapToProductResponse);
    }

    @Override
    public @Nullable Page<ProductResponse> searchProductPage(String name, String category, Double minPrice, Double maxPrice, Boolean availability, Pageable pageable) {
        Page<Product> page=productRepository.searchProductByPage(name,category,minPrice,maxPrice,availability,pageable);
        return page.map(mapper::mapToProductResponse);
    }
}
