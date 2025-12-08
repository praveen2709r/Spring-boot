package com.Application.HelloWorld.Controller;


import com.Application.HelloWorld.DTO.ProductRequestDTO;
import com.Application.HelloWorld.DTO.ProductResponseDTO;
import com.Application.HelloWorld.ExceptionHandling.ProductNotFoundException;
import com.Application.HelloWorld.Service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product/api")
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping("/findAll")
    public ResponseEntity<List<ProductResponseDTO>> getAll(){
        log.info("Inside the findAll product controller");
        return new ResponseEntity<>(productService.findAll(),HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<ProductResponseDTO> saveProducts(@Valid @RequestBody ProductRequestDTO productRequestDTO){
        log.info("Inside save product controller");
        return new ResponseEntity<>(productService.save(productRequestDTO), HttpStatus.CREATED);
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable("id") Long productId) throws ProductNotFoundException {
        log.info("Inside findById product Controller");
        return new ResponseEntity<>(productService.findById(productId),HttpStatus.OK);
    }
    @GetMapping("/findByName/{name}")
    public ResponseEntity<List<ProductResponseDTO>> findByProductName(@PathVariable("name") String productName) throws ProductNotFoundException {
        log.info("Inside findByName product controller");
       return new ResponseEntity<>(productService.findByProductName(productName),HttpStatus.OK);
    }
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long productId) throws ProductNotFoundException {
        log.info("Inside deleteById product controller");
        if(productService.deleteById(productId))
             return new ResponseEntity<>("Product deleted successfully",HttpStatus.OK);
        return new ResponseEntity<>("Product is not deleted",HttpStatus.NOT_FOUND);
    }
    @PutMapping("/updateById/{id}")
    public ResponseEntity<ProductResponseDTO> updateById(@PathVariable("id") Long productId, @Valid @RequestBody ProductRequestDTO productRequestDTO) throws ProductNotFoundException {
        log.info("Inside updateById product controller");
        return new ResponseEntity<>(productService.updateById(productId,productRequestDTO),HttpStatus.OK);
    }
    @GetMapping("/csrf-token")
    public CsrfToken getCSRFToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
