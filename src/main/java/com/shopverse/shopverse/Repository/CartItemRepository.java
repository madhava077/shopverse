package com.shopverse.shopverse.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.shopverse.shopverse.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    
}
