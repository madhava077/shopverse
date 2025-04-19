package com.shopverse.shopverse.Dto;

public class CartItemDto {
    private long id;
    private long userid;
    private long productid;
    private int quantity;
    public CartItemDto() {
    }
    public CartItemDto(long id, long userid, long productid, int quantity) {
        this.id = id;
        this.userid = userid;
        this.productid = productid;
        this.quantity = quantity;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getUserid() {
        return userid;
    }
    public void setUserid(long userid) {
        this.userid = userid;
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
    
}
