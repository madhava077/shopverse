package com.shopverse.shopverse.ServiceImp;

import org.springframework.beans.factory.annotation.Autowired;

import com.shopverse.shopverse.Dto.OrderItemDto;
import com.shopverse.shopverse.Exception.OrderItemException;
import com.shopverse.shopverse.Repository.OrderItemRepository;
import com.shopverse.shopverse.Service.OrderItemService;
import com.shopverse.shopverse.model.OrderItem;
public class OrderItemServiceImp implements OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired 
    private OrderServiceImp orderServiceImp;
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
        orderItem.setOrder(orderServiceImp.getOrderByIdReturnOrder(orderItemDto.getOrderid()));
        orderItem.setProduct(productServiceImp.getProductByIdReturnProduct(orderItemDto.getProductid()));
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setPrice(orderItemDto.getPrice());
        return orderItem;
    }
    private OrderItemDto EntityTOOrderItemDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setOrderid(orderItem.getOrder().getId());
        orderItemDto.setProductid(orderItem.getProduct().getId());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setPrice(orderItem.getPrice());
        return orderItemDto;
    }
    public OrderItemDto getOrderItemById(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(() -> new OrderItemException(String.format("OrderItem with id %d not found", id)));
        return EntityTOOrderItemDto(orderItem);
    }
    public OrderItemDto updateOrderItem(Long id, OrderItemDto orderItemDto) {
        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(() -> new OrderItemException(String.format("OrderItem with id %d not found", id)));
        
            orderItem.setOrder(orderServiceImp.getOrderByIdReturnOrder(orderItemDto.getOrderid()));
            orderItem.setProduct(productServiceImp.getProductByIdReturnProduct(orderItemDto.getProductid()));
            orderItem.setQuantity(orderItemDto.getQuantity());
            orderItem.setPrice(orderItemDto.getPrice());
            OrderItem updatedOrderItem = orderItemRepository.save(orderItem);
            return EntityTOOrderItemDto(updatedOrderItem);
    }
    public void deleteOrderItem(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(() -> new OrderItemException(String.format("OrderItem with id %d not found", id)));
        orderItemRepository.delete(orderItem);
    }


}
