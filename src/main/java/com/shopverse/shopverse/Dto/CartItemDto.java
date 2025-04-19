package com.shopverse.shopverse.Dto;

public class CartItemDto {
    private Long id;
    private Long userid;
    private Long productid;
    private int quantity;
    public CartItemDto() {
    }
    public CartItemDto(Long id, Long userid, Long productid, int quantity) {
        this.id = id;
        this.userid = userid;
        this.productid = productid;
        this.quantity = quantity;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserid() {
        return userid;
    }
    public void setUserid(Long userid) {
        this.userid = userid;
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
