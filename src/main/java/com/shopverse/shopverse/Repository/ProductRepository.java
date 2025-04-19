package com.shopverse.shopverse.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shopverse.shopverse.model.Product;


public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByProductname(String productname);
    Optional<Product> findByCategory(String category);
}

