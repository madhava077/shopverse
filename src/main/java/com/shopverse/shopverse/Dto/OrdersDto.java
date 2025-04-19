package com.shopverse.shopverse.Dto;

import java.time.LocalDate;

public class OrdersDto {
    private Long id;
    private Long user_id;
    private LocalDate orderDate;
    private double totalAmount;
    private String status;
    public OrdersDto() {
    }
    public OrdersDto(Long id, Long user_id, LocalDate orderDate, double totalAmount, String status) {
        this.id = id;
        this.user_id = user_id;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUser_id() {
        return user_id;
    }
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
    public LocalDate getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
}
