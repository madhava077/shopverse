package com.shopverse.shopverse.ServiceImp;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopverse.shopverse.Repository.OrdersRepository;

import com.shopverse.shopverse.Service.OrderService;
import com.shopverse.shopverse.model.Orders;
import com.shopverse.shopverse.Dto.OrdersDto;
import com.shopverse.shopverse.Exception.OrderException;
@Service
public class OrderServiceImp implements OrderService
{
    @Autowired
    private OrdersRepository ordersRepository;
   
    @Autowired
    private UserServiceImp userServiceImp;
    public OrdersDto placeOrder(OrdersDto orderDto)
    {
        Orders order=OrderDtoTOEntity(orderDto);
        Orders savedOrder=ordersRepository.save(order);
        OrdersDto savedOrderDto=EntityTOOrderDto(savedOrder);
        return savedOrderDto;
    }

    private Orders OrderDtoTOEntity(OrdersDto orderDto)
    {
        Orders order=new Orders();
        order.setUser(userServiceImp.getUserByIdReturnUser(orderDto.getUser_id()));
        order.setOrderDate(orderDto.getOrderDate());
        order.setTotalAmount(orderDto.getTotalAmount());
        order.setStatus(orderDto.getStatus());
        return order;
    }
    private OrdersDto EntityTOOrderDto(Orders order)
    {
        OrdersDto orderDto=new OrdersDto();
        orderDto.setId(order.getId());
        orderDto.setUser_id(order.getUser().getId());
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setTotalAmount(order.getTotalAmount());
        orderDto.setStatus(order.getStatus());
        return orderDto;
    }
    public OrdersDto getOrderById(Long id)
    {
        Orders order=ordersRepository.findById(id).orElseThrow(() ->new OrderException(String.format("Order with id %d not found", id)));
        return EntityTOOrderDto(order);
    }
    public List<OrdersDto> getAllOrders()
    {
        List<Orders> orders=ordersRepository.findAll();
        List<OrdersDto> orderDtos=orders.stream().map(this::EntityTOOrderDto).collect(Collectors.toList());
        return orderDtos;
    }
    
    public OrdersDto updateOrder(Long id, OrdersDto orderDto)
    {
        Orders order=ordersRepository.findById(id).orElseThrow(() ->new OrderException(String.format("Order with id %d not found", id)));
        
            order.setUser(userServiceImp.getUserByIdReturnUser(orderDto.getUser_id()));
            order.setOrderDate(orderDto.getOrderDate());
            order.setTotalAmount(orderDto.getTotalAmount());
            order.setStatus(orderDto.getStatus());
            Orders updatedOrder=ordersRepository.save(order);
            return EntityTOOrderDto(updatedOrder);
     }
    public void deleteOrder(Long id)
    {
        Orders order=ordersRepository.findById(id).orElseThrow(() ->new OrderException(String.format("Order with id %d not found", id)));
        ordersRepository.delete(order);
    }
    public Orders getOrderByIdReturnOrder(Long id)
    {
        Orders order=ordersRepository.findById(id).orElseThrow(() ->new OrderException(String.format("Order with id %d not found", id)));
        return order;
    }
}
