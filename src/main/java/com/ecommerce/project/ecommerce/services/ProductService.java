package com.ecommerce.project.ecommerce.services;

import com.ecommerce.project.ecommerce.dto.ProductDto;
import com.ecommerce.project.ecommerce.entities.ProductEntity;
import com.ecommerce.project.ecommerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public ProductDto createProduct(ProductDto productDto) {

        ProductEntity productEntity = new ProductEntity();

        productEntity.setNome(productDto.getNome());
        productEntity.setPreco(productDto.getPreco());
        productEntity.setQuantidade(productDto.getQuantidade());
        productEntity.setDescricao(productDto.getDescricao());
        productEntity.setAtivo(productDto.isAtivo());
        productEntity.setData(productDto.getData());

        productRepository.save(productEntity);

        return productDto;

    }

}
