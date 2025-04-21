package com.shopverse.shopverse.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopverse.shopverse.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long>{
    
    
}
