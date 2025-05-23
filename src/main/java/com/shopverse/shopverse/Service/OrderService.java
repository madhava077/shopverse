package com.shopverse.shopverse.Service;

import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;

import com.shopverse.shopverse.Dto.OrdersDto;
import com.shopverse.shopverse.model.Orders;

public interface OrderService {

    public OrdersDto placeOrder(OrdersDto ordersDto);

    public OrdersDto getOrderById(Long id);

    public OrdersDto updateOrder(Long id, OrdersDto ordersDto);

    public void deleteOrder(Long id);

    public OrdersDto createOrder(Long userId);
    public Orders getOrderByIdReturnOrder(Long id);
    
}
