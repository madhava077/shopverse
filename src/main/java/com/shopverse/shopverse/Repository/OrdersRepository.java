package com.shopverse.shopverse.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopverse.shopverse.model.Orders;




public interface OrdersRepository extends JpaRepository<Orders,Long>{
List<Orders> findByUserId(Long userId);
}
