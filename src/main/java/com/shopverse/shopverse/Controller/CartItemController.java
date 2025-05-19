package com.shopverse.shopverse.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopverse.shopverse.Dto.CartItemDto;

import com.shopverse.shopverse.Service.CartItemService;



import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;
    @PostMapping("/create")
    public ResponseEntity<CartItemDto> createCartItem(@RequestBody CartItemDto cartItemDto) {
        CartItemDto createdCartItem = cartItemService.CreateCartItem(cartItemDto);
        return ResponseEntity.status(201).body(createdCartItem);
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<List<CartItemDto>> getCartItems(@PathVariable Long userId) {
    List<CartItemDto> cartItems = cartItemService.getCartItemsForUser(userId);
    return ResponseEntity.ok(cartItems);
}
    @GetMapping("/getcartitem/{id}")
    public ResponseEntity<CartItemDto> getCartItemById(@PathVariable Long id) {
        CartItemDto cartItem = cartItemService.getCartItemById(id);
        return ResponseEntity.ok(cartItem);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<CartItemDto> updateCartItem(@PathVariable Long id, @RequestBody CartItemDto cartItemDto) {
        CartItemDto updatedCartItem = cartItemService.updateCartItem(id, cartItemDto);
        return ResponseEntity.ok(updatedCartItem);
    }
   @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCartItem(@PathVariable Long id) {
        cartItemService.deleteCartItem(id);
        return ResponseEntity.ok("Cart item deleted successfully");
    }


}
