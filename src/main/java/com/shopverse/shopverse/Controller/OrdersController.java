package com.shopverse.shopverse.Controller;

import com.shopverse.shopverse.Dto.OrdersDto;
import com.shopverse.shopverse.Service.CartItemService;
import com.shopverse.shopverse.Service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CartItemService cartItemService;
    //@PostMapping("/{userId}")
//public ResponseEntity<OrdersDto> confirmOrder(@PathVariable Long userId) {
    
  //  OrdersDto confirmedOrder = orderService.placeOrderForUser(userId);


    //cartItemService.clearCartForUser(userId);

    //return ResponseEntity.ok(confirmedOrder);
//}
    @PostMapping
    public ResponseEntity<OrdersDto> placeOrder(@RequestBody OrdersDto ordersDto) {
        OrdersDto placedOrder = orderService.placeOrder(ordersDto);
        return ResponseEntity.ok(placedOrder);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Order deleted successfully");
    }
}
