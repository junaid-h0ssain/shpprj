package com.dokan.shppr.controllers;

import com.dokan.shppr.entities.Products;
import com.dokan.shppr.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public Products createProduct(@Valid @RequestBody Products products){
        return productService.createProduct(products);
    }

    @PutMapping("/{id}")
    public Products updateProduct(@PathVariable Long id, @Valid @RequestBody Products products){
        return productService.updateProduct(id, products);
    }

    @GetMapping
    public List<Products> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Products getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @DeleteMapping("/{id}")
    public Products deleteById(@PathVariable Long id){
        return productService.deleteById(id);
    }
}
