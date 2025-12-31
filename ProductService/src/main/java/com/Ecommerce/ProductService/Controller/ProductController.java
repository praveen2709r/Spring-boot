package com.Ecommerce.ProductService.Controller;

import com.Ecommerce.ProductService.DTO.ProductRequest;
import com.Ecommerce.ProductService.DTO.ProductResponse;
import com.Ecommerce.ProductService.Service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest){
        log.info("Creating Product: {}",productRequest.getProductName());
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(productRequest));
    }
    @GetMapping("/allProductsbyPage")
    public ResponseEntity<Page<ProductResponse>> getAllProducts(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "10")int size,
            @RequestParam(defaultValue = "productId")String sortBy,
            @RequestParam(defaultValue = "asc")String direction
            )
    {
        Sort sort=direction.equalsIgnoreCase("asc")
                ?Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();
        Pageable pageable=PageRequest.of(page,size,sort);
        return ResponseEntity.ok(productService.getAllProducts(pageable));
    }
    @Operation(summary="Get all products with pagination & sorting")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Products fetched successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid pagination parameters")
    })
    @GetMapping("/allProducts")
    public ResponseEntity<List<ProductResponse>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }
    @GetMapping("{id}")
    public ResponseEntity<ProductResponse> findByProductId(@PathVariable("id") Long productId){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findByProductId(productId));
    }
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponse> updateByProductId(@RequestBody ProductRequest productRequest,
                                             @PathVariable("id") Long productId){
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateByProductId(productRequest,productId));
    }
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteByProductId(@PathVariable("id") Long productId){
        productService.deleteById(productId);
        return ResponseEntity.noContent().build();
    }
   @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
    @RequestParam(required = false) Double maxPrice,
    @RequestParam(required = false)  Boolean availability
    ){
        List<ProductResponse> productResponses=productService.searchProduct(name,category,minPrice,maxPrice,availability);
        return ResponseEntity.ok(productResponses);
    }
    @GetMapping("/searchPage")
    public ResponseEntity<Page<ProductResponse>> searchProductsByPage(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false)  Boolean availability,
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "10")int size,
            @RequestParam(defaultValue = "productId")String sortBy,
            @RequestParam(defaultValue = "asc")String direction
    ){
        Sort sort=direction.equalsIgnoreCase("asc")
                ?Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();
        Pageable pageable=PageRequest.of(page,size,sort);
        return ResponseEntity.ok(productService.searchProductPage(name,category,minPrice,maxPrice,availability,pageable));
    }
}
