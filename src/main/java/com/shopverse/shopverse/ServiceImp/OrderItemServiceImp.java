package com.shopverse.shopverse.ServiceImp;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.shopverse.shopverse.Dto.CartItemDto;
import com.shopverse.shopverse.Dto.OrderItemDto;
import com.shopverse.shopverse.Exception.OrderItemException;
import com.shopverse.shopverse.Repository.OrderItemRepository;

import com.shopverse.shopverse.Service.OrderItemService;
import com.shopverse.shopverse.Service.OrderService;
import com.shopverse.shopverse.model.OrderItem;


@Service
public class OrderItemServiceImp implements OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Lazy
    @Autowired 
    private OrderService orderService;
    @Autowired
    private CartItemServiceImp cartItemServiceImp;
    

    @Autowired
    private ProductServiceImp productServiceImp;
    public OrderItemDto createOrderItem(OrderItemDto orderItemDto) {
        OrderItem orderItem = OrderItemDtoTOEntity(orderItemDto);
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        OrderItemDto savedOrderItemDto = EntityTOOrderItemDto(savedOrderItem);
        return savedOrderItemDto;
    }
    private OrderItem OrderItemDtoTOEntity(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(orderService.getOrderByIdReturnOrder(orderItemDto.getOrderid()));
        orderItem.setProduct(productServiceImp.getProductByIdReturnProduct(orderItemDto.getProductid()));
        orderItem.setQuantity(orderItemDto.getQuantity());
       
        return orderItem;
    }
    private OrderItemDto EntityTOOrderItemDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setOrderid(orderItem.getOrder().getId());
        orderItemDto.setProductid(orderItem.getProduct().getId());
        orderItemDto.setQuantity(orderItem.getQuantity());
    
        return orderItemDto;
    }
    public void OrderItems(Long userId){
        List<CartItemDto> cartItems = cartItemServiceImp.getCartItemsForUser(userId);
        for (CartItemDto cartItem : cartItems) {
            OrderItemDto orderItemDto = new OrderItemDto();
            orderItemDto.setOrderid(cartItem.getUserid());
            orderItemDto.setProductid(cartItem.getProductid());
            orderItemDto.setQuantity(cartItem.getQuantity());

          
            createOrderItem(orderItemDto);

    }
        cartItemServiceImp.clearCartForUser(userId);
    
    }
    public OrderItemDto getOrderItemById(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(() -> new OrderItemException(String.format("OrderItem with id %d not found", id)));
        return EntityTOOrderItemDto(orderItem);
    }
    public OrderItemDto updateOrderItem(Long id, OrderItemDto orderItemDto) {
        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(() -> new OrderItemException(String.format("OrderItem with id %d not found", id)));
        
            orderItem.setOrder(orderService.getOrderByIdReturnOrder(orderItemDto.getOrderid()));
            orderItem.setProduct(productServiceImp.getProductByIdReturnProduct(orderItemDto.getProductid()));
            orderItem.setQuantity(orderItemDto.getQuantity());
            
            OrderItem updatedOrderItem = orderItemRepository.save(orderItem);
            return EntityTOOrderItemDto(updatedOrderItem);
    }
    public void deleteOrderItem(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(() -> new OrderItemException(String.format("OrderItem with id %d not found", id)));
        orderItemRepository.delete(orderItem);
    }


}
