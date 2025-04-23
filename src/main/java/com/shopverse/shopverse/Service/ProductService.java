package com.shopverse.shopverse.Service;

import java.util.List;

import com.shopverse.shopverse.Dto.ProductDto;
import com.shopverse.shopverse.model.Product;

public interface ProductService {
    public ProductDto createProduct(ProductDto productDto);
    public ProductDto getProductById(Long id);
    public ProductDto updateProduct(Long id, ProductDto productDto);
    public void deleteProduct(Long id);
    public Product getProductByIdReturnProduct(Long id);
    public List<ProductDto> searchProducts(String keyword);
}
