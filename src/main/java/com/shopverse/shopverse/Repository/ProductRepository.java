package com.shopverse.shopverse.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopverse.shopverse.model.Product;


public interface ProductRepository extends JpaRepository<Product,Long> {
List<Product> findByProductnameContainingIgnoreCase(String keyword);
}

