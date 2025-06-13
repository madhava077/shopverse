package com.shopverse.shopverse.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopverse.shopverse.model.CartItem;
@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    List<CartItem> findByUser_Id(Long userId);
    void deleteByUser_Id(Long userId);
}
