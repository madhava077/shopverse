package com.shopverse.shopverse.Service;

import com.shopverse.shopverse.Dto.OrderItemDto;


public interface OrderItemService {

    OrderItemDto createOrderItem(OrderItemDto orderItemDto);

    OrderItemDto getOrderItemById(Long id);

    OrderItemDto updateOrderItem(Long id, OrderItemDto orderItemDto);
    
    public void deleteOrderItem(Long id);
    
}
