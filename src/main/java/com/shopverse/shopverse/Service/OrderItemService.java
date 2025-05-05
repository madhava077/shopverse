package com.shopverse.shopverse.Service;

import com.shopverse.shopverse.Dto.OrderItemDto;


public interface OrderItemService {

    public OrderItemDto createOrderItem(OrderItemDto orderItemDto);

    public OrderItemDto getOrderItemById(Long id);

    public OrderItemDto updateOrderItem(Long id, OrderItemDto orderItemDto);
    
    public void deleteOrderItem(Long id);
    
}
