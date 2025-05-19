package com.shopverse.shopverse.Dto;

public class OrderItemDto {
    private Long id;
    private Long orderid;
    private Long productid;
    private int quantity;
    
    public OrderItemDto() {
    }
    public OrderItemDto(Long id, Long orderid, Long productid, int quantity, double price) {
        this.id = id;
        this.orderid = orderid;
        this.productid = productid;
        this.quantity = quantity;
      
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderid() {
        return orderid;
    }
    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }
    public Long getProductid() {
        return productid;
    }
    public void setProductid(Long productid) {
        this.productid = productid;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
  
    
    
}
