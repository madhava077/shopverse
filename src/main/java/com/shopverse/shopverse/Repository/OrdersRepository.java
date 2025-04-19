package com.shopverse.shopverse.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopverse.shopverse.model.Orders;
import java.util.List;
import java.time.LocalDate;



public interface OrdersRepository extends JpaRepository<Orders,Long>{
    List<Orders> findByOrderDate(LocalDate orderDate);
}
