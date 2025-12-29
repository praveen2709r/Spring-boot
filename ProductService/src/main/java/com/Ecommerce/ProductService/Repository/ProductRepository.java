package com.Ecommerce.ProductService.Repository;

import com.Ecommerce.ProductService.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByActiveTrue();
    @Query("""
            SELECT p FROM Product p WHERE
            (:name IS NULL OR LOWER(p.productName) LIKE LOWER(CONCAT('%', :name, '%')))
            AND (:category IS NULL OR p.category = :category)
            AND (:minPrice IS NULL OR p.productPrice >= :minPrice)
            AND (:maxPrice IS NULL OR p.productPrice <= :maxPrice)
            AND (:availability IS NULL OR p.availability = :availability)
            """)
    List<Product> searchProducts(@Param("name")String name,
                                 @Param("category")String category,
                                 @Param("minPrice") Double minPrice,
                                 @Param("maxPrice") Double maxPrice,
                                 @Param("availability") Boolean availability);



    @Query("""
            SELECT p FROM Product p WHERE
            (:name IS NULL OR LOWER(p.productName) LIKE LOWER(CONCAT('%', :name, '%')))
            AND (:category IS NULL OR p.category = :category)
            AND (:minPrice IS NULL OR p.productPrice >= :minPrice)
            AND (:maxPrice IS NULL OR p.productPrice <= :maxPrice)
            AND (:availability IS NULL OR p.availability = :availability)
            """)
    Page<Product> searchProductByPage(String name, String category, Double minPrice, Double maxPrice, Boolean availability, Pageable pageable);
}
