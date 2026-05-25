package com.dokan.shppr.repository;

import com.dokan.shppr.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Products, Long> {

}
