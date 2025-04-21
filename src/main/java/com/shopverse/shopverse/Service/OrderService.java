package com.shopverse.shopverse.Service;

import com.shopverse.shopverse.Dto.OrdersDto;

public interface OrderService {

    OrdersDto placeOrder(OrdersDto ordersDto);

    OrdersDto getOrderById(Long id);

    OrdersDto updateOrder(Long id, OrdersDto ordersDto);

    void deleteOrder(Long id);


    
}
