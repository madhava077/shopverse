package com.shopverse.shopverse.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.shopverse.shopverse.model.Product;


public interface ProductRepository extends JpaRepository<Product,Long> {

}

