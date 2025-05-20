package com.shopverse.shopverse.Dto;

public class ProductDto {
	private Long id;
	private String productname;
	private String imageUrl;
	private String description;
	private double price;
	private int stock;
	private String category;
	public ProductDto() {
	}
	public ProductDto(Long id, String productname, String description, double price, int stock, String category) {
		this.id = id;
		this.productname = productname;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.category = category;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
}
