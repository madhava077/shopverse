package com.shopverse.shopverse.ServiceImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.shopverse.shopverse.Dto.ProductDto;
import com.shopverse.shopverse.Exception.ProductException;
import com.shopverse.shopverse.Repository.ProductRepository;
import com.shopverse.shopverse.Service.ProductService;
import com.shopverse.shopverse.model.Product;

public class ProductServiceImp implements ProductService {
    @Autowired
    ProductRepository productRepository;
    public ProductDto createProduct(ProductDto productDto) {
    Product product =ProductDtoTOEntity(productDto);
    Product savedProduct = productRepository.save(product);
    ProductDto savedProductDto =EntityTOProductDto(savedProduct);
    return savedProductDto;
    }
    private Product ProductDtoTOEntity(ProductDto productDto) {
         Product product = new Product();
         product.setProductname(productDto.getProductname());
         product.setDescription(productDto.getDescription());
         product.setPrice(productDto.getPrice());
         product.setStock(productDto.getStock());
         product.setCategory(productDto.getCategory());
         return product;
     }
     private ProductDto EntityTOProductDto(Product product) {
         ProductDto productDto = new ProductDto();
         productDto.setId(product.getId());
         productDto.setProductname(product.getProductname());
         productDto.setDescription(product.getDescription());
         productDto.setPrice(product.getPrice());
         productDto.setStock(product.getStock());
         productDto.setCategory(product.getCategory());
         return productDto;
    }
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->new ProductException(String.format("Product with id %d not found", id)));
        return EntityTOProductDto(product);
    }
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id).orElseThrow(() ->new ProductException(String.format("Product with id %d not found", id)));
        
            product.setProductname(productDto.getProductname());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            product.setStock(productDto.getStock());
            product.setCategory(productDto.getCategory());
            Product updatedProduct = productRepository.save(product);
            return EntityTOProductDto(updatedProduct);
     }
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->new ProductException(String.format("Product with id %d not found", id)));
        productRepository.delete(product);
    
}
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            productDtos.add(EntityTOProductDto(product));
        }
        return productDtos;
}
    public Product getProductByIdReturnProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->new ProductException(String.format("Product with id %d not found", id)));
        return product;
    }
}
