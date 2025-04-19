package com.shopverse.shopverse.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shopverse.shopverse.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    Optional<CartItem> findByProductIdAndUserId(Long productId, Long userId);
    void deleteByProductIdAndUserId(Long productId, Long userId);
    Optional<CartItem> findByUserId(Long userId);
}
