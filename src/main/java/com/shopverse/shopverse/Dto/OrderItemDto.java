package com.shopverse.shopverse.Dto;

public class OrderItemDto {
    private long id;
    private long orderid;
    private long productid;
    private int quantity;
    private double price;
    public OrderItemDto() {
    }
    public OrderItemDto(long id, long orderid, long productid, int quantity, double price) {
        this.id = id;
        this.orderid = orderid;
        this.productid = productid;
        this.quantity = quantity;
        this.price = price;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getOrderid() {
        return orderid;
    }
    public void setOrderid(long orderid) {
        this.orderid = orderid;
    }
    public long getProductid() {
        return productid;
    }
    public void setProductid(long productid) {
        this.productid = productid;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    
}
