package com.dokan.shppr.services;

import com.dokan.shppr.entities.Products;
import com.dokan.shppr.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;

    public Products createProduct( Products products){
        return productRepo.save(products);
    }

    public Products updateProduct( Long id,  Products products){

        Products existingProduct = (productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found by Id" + id)));

        existingProduct.setName(products.getName());
        existingProduct.setDescription(products.getDescription());
        existingProduct.setPrice(products.getPrice());
        existingProduct.setStock_quantity(products.getStock_quantity());

        return productRepo.save(existingProduct);
    }

    public List<Products> getProducts(){
        return productRepo.findAll();
    }


    public Products getProductById( Long id){
        return productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found by Id" + id));
    }


    public Products deleteById(Long id){
        Products product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found by Id" + id));
        productRepo.delete(product);
        return product;
    }
}
