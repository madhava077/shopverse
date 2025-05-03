package com.shopverse.shopverse.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopverse.shopverse.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    public List<CartItem> findByUserId(Long userId);
    public void deleteByUserId(Long userId);
}
