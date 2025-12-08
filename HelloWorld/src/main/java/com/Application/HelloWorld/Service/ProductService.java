package com.Application.HelloWorld.Service;

import com.Application.HelloWorld.DTO.Mapper;
import com.Application.HelloWorld.DTO.ProductRequestDTO;
import com.Application.HelloWorld.DTO.ProductResponseDTO;
import com.Application.HelloWorld.Entity.Product;
import com.Application.HelloWorld.ExceptionHandling.ProductNotFoundException;
import com.Application.HelloWorld.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<ProductResponseDTO> findAll() {
        List<Product> product=productRepository.findAll();
        return product.stream().map(Mapper::getProductResponseDTO).toList();
    }   

    public ProductResponseDTO save(ProductRequestDTO productRequestDTO) {
        Product product=Mapper.getProductRequestDTO(productRequestDTO);
        productRepository.save(product);
        return Mapper.getProductResponseDTO(product);
    }

    public ProductResponseDTO findById(Long id) throws ProductNotFoundException {
        Optional<Product> product=productRepository.findById(id);
        if(product.isEmpty()){
            throw new ProductNotFoundException("Product is not found");
        }
        return Mapper.getProductResponseDTO(product.get());
    }

    public List<ProductResponseDTO> findByProductName(String productName) throws ProductNotFoundException {
       List<Product> product= productRepository.findByProductName(productName);
       if(product.isEmpty()){
           throw new ProductNotFoundException("Products are not found");
       }
        return product.stream().map(Mapper::getProductResponseDTO).toList();
    }

    public boolean deleteById(Long productId) throws ProductNotFoundException {
        Optional<Product> product=productRepository.findById(productId);
        if(product.isEmpty())
            throw new ProductNotFoundException("Product is not found");
        productRepository.deleteById(productId);
        return true;
    }

    public ProductResponseDTO updateById(Long productId, ProductRequestDTO productRequestDTO) throws ProductNotFoundException {
        Optional<Product> product=productRepository.findById(productId);
        if(product.isEmpty())
            throw new ProductNotFoundException("Product is not found");
        product.get().setProductName(productRequestDTO.getProductName());
        product.get().setModelNumber(productRequestDTO.getModelNumber());
        product.get().setAvailability(productRequestDTO.getAvailability());
        product.get().setPrice(productRequestDTO.getPrice());
        productRepository.save(product.get());
        return Mapper.getProductResponseDTO(product.get());
    }
}
