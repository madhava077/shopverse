package com.shopverse.shopverse.Controller;

import com.shopverse.shopverse.Dto.OrdersDto;

import com.shopverse.shopverse.Service.OrderService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    @Autowired
    private OrderService orderService;
    @PostMapping("/{id}")
    public ResponseEntity<OrdersDto> createOrder(@PathVariable Long id) {
        OrdersDto createdOrder = orderService.createOrder(id);
        return ResponseEntity.ok(createdOrder);
    }
    @GetMapping("/user/{userId}")
public ResponseEntity<List<OrdersDto>> getOrdersByUserId(@PathVariable Long userId) {
    List<OrdersDto> orders = orderService.getOrdersByUserIdDtos(userId);
    return ResponseEntity.ok(orders);
}

    @GetMapping("/{id}")
    public ResponseEntity<OrdersDto> getOrderById(@PathVariable Long id) {
        OrdersDto order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }
    @PutMapping("/{id}")
    public ResponseEntity<OrdersDto> updateOrder(@PathVariable Long id, @RequestBody OrdersDto ordersDto) {
        OrdersDto updatedOrder = orderService.updateOrder(id, ordersDto);
        return ResponseEntity.ok(updatedOrder);
    }
    @GetMapping("/all")
    public ResponseEntity<List<OrdersDto>> getAllOrders() {
        List<OrdersDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
    @PutMapping("/cancel/{id}")
public ResponseEntity<OrdersDto> cancelOrder(@PathVariable Long id) {
    OrdersDto order = orderService.getOrderById(id);
    order.setStatus("Canceled");
    OrdersDto updatedOrder = orderService.updateOrder(id, order);
    return ResponseEntity.ok(updatedOrder);
}
    

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Order deleted successfully");
    }
}
