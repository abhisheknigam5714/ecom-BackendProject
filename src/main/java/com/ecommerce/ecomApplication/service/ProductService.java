package com.ecommerce.ecomApplication.service;

import com.ecommerce.ecomApplication.dtos.ProductRequest;
import com.ecommerce.ecomApplication.dtos.ProductResponse;
import com.ecommerce.ecomApplication.model.Product;
import com.ecommerce.ecomApplication.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    public ProductResponse createProduct(ProductRequest productReq) {
        Product product = new Product();
        convertRequestToProduct(product,productReq);
                productRepository.save(product);
                return mapProductToResponse(product);
    }

    private ProductResponse mapProductToResponse(Product product) {
    ProductResponse productRes= new ProductResponse();
        productRes.setId(product.getId());
        productRes.setName(product.getName());
        productRes.setActive(product.getActive());
        productRes.setCategory(product.getCategory());
        productRes.setDescription(product.getDescription());
        productRes.setImageUrl(product.getImageUrl());
        productRes.setPrice(product.getPrice());
        productRes.setStockQuantity(product.getStockQuantity());
        return productRes;
    }

    private void convertRequestToProduct(Product product, ProductRequest productReq) {
        product.setName(productReq.getName());
        product.setCategory(productReq.getCategory());
        product.setDescription(productReq.getDescription());
        product.setImageUrl(productReq.getImageUrl());
        product.setPrice(productReq.getPrice());
        product.setStockQuantity(productReq.getStockQuantity());
    }

    public ProductResponse updateProduct(Long id, ProductRequest productReq) {
      return productRepository.findById(id).map(existing->{
          convertRequestToProduct(existing,productReq);
          Product save = productRepository.save(existing);
          return mapProductToResponse(save);
      }).orElseThrow(()-> new RuntimeException("product Not found "));
    }
}
