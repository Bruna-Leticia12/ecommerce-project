package com.ecommerce.project.ecommerce.services;

import com.ecommerce.project.ecommerce.dto.ProductDto;
import com.ecommerce.project.ecommerce.entities.ProductEntity;
import com.ecommerce.project.ecommerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<ProductDto> getProducts(){
         List<ProductEntity> productEntities= productRepository.findAll();

         List<ProductDto> productDtos = productEntities.stream().map(elemento -> {
             ProductDto productDto = new ProductDto();

             productDto.setNome(elemento.getNome());
             productDto.setPreco(elemento.getPreco());
             productDto.setQuantidade(elemento.getQuantidade());
             productDto.setDescricao(elemento.getDescricao());
             productDto.setAtivo(elemento.isAtivo());
             productDto.setData(elemento.getData());

             return productDto;
         }).collect(Collectors.toList());

         return  productDtos;
    }

}
