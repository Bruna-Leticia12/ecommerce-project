package com.ecommerce.project.ecommerce.service;

import com.ecommerce.project.ecommerce.dto.ProductDto;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public ProductDto createProduct() {
        return new ProductDto("aquecedor", 1000.00);
    }

}
