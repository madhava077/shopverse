package com.shopverse.shopverse.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopverse.shopverse.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long>{
    List<OrderItem> findByOrderId(Long orderId);
    
}
