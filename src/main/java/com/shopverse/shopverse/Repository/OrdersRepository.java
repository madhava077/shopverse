package com.shopverse.shopverse.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopverse.shopverse.model.Orders;




public interface OrdersRepository extends JpaRepository<Orders,Long>{

}
