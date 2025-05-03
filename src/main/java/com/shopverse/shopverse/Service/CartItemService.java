package com.shopverse.shopverse.Service;

import java.util.List;

import com.shopverse.shopverse.Dto.CartItemDto;


public interface CartItemService {
     public CartItemDto CreateCartItem(CartItemDto cartItemDto);
     public CartItemDto getCartItemById(Long id);
     public CartItemDto updateCartItem(Long id, CartItemDto cartItemDto);
     public void deleteCartItem(Long id);
     public List<CartItemDto> getCartItemsForUser(Long userId);
     public void clearCartForUser(Long userId);
}
