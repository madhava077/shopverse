package com.shopverse.shopverse.ServiceImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopverse.shopverse.Dto.CartItemDto;

import com.shopverse.shopverse.Exception.UserException;
import com.shopverse.shopverse.Repository.CartItemRepository;
import com.shopverse.shopverse.Service.CartItemService;

import com.shopverse.shopverse.model.CartItem;

import jakarta.transaction.Transactional;

@Service
public class CartItemServiceImp implements CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductServiceImp productServiceImp;
    @Autowired
    private UserServiceImp userServiceImp;


    public CartItemDto CreateCartItem(CartItemDto cartItemDto) {
        CartItem cartItem = CartItemDtoTOEntity(cartItemDto);
        CartItem savedCartItem = cartItemRepository.save(cartItem);
        CartItemDto savedCartItemDto = EntityTOCartItemDto(savedCartItem);
        return savedCartItemDto;
    }

    private CartItem CartItemDtoTOEntity(CartItemDto cartItemDto) {
        CartItem cartItem = new CartItem();
        cartItem.setUser(userServiceImp.getUserByIdReturnUser(cartItemDto.getUserid()));
        cartItem.setProduct(productServiceImp.getProductByIdReturnProduct(cartItemDto.getProductid()));
        cartItem.setQuantity(cartItemDto.getQuantity());
        return cartItem;
    }
    private CartItemDto EntityTOCartItemDto(CartItem cartItem) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setId(cartItem.getId());
        cartItemDto.setUserid(cartItem.getUser().getId());
        cartItemDto.setProductid(cartItem.getProduct().getId());
        cartItemDto.setQuantity(cartItem.getQuantity());
        return cartItemDto;
    }
    public List<CartItemDto> getCartItemsForUser(Long userId) {
        List<CartItem> cartItems = cartItemRepository.findByUser_Id(userId);
        List<CartItemDto> cartItemDtos = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            CartItemDto cartItemDto = EntityTOCartItemDto(cartItem);
            cartItemDtos.add(cartItemDto);
        }
        return cartItemDtos;
    }
    @Transactional
    public void clearCartForUser(Long userId) {
        cartItemRepository.deleteByUser_Id(userId);
    }
    public Double getTotalPriceForUser(Long userId) {
        List<CartItem> cartItems = cartItemRepository.findByUser_Id(userId);
        double totalPrice = 0.0;
        for (CartItem cartItem : cartItems) {
            totalPrice += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        return totalPrice;
    }
    
    public CartItemDto getCartItemById(Long id) {
        CartItem cartItem = cartItemRepository.findById(id).orElseThrow(() -> new UserException(String.format("CartItem with id %d not found", id)));
        return EntityTOCartItemDto(cartItem);
    }
    public CartItemDto updateCartItem(Long id, CartItemDto cartItemDto) {
        CartItem cartItem = cartItemRepository.findById(id).orElseThrow(() -> new UserException(String.format("CartItem with id %d not found", id)));
        
            cartItem.setUser(userServiceImp.getUserByIdReturnUser(cartItemDto.getUserid()));
            cartItem.setProduct(productServiceImp.getProductByIdReturnProduct(cartItemDto.getProductid()));
            cartItem.setQuantity(cartItemDto.getQuantity());
            CartItem updatedCartItem = cartItemRepository.save(cartItem);
            return EntityTOCartItemDto(updatedCartItem);
    }
    public void deleteCartItem(Long id) {
        CartItem cartItem = cartItemRepository.findById(id).orElseThrow(() -> new UserException(String.format("CartItem with id %d not found", id)));
        cartItemRepository.delete(cartItem);
    }
   

}
