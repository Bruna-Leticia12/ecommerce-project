package com.ecommerce.project.ecommerce.services;

import com.ecommerce.project.ecommerce.dto.ProductDto;
import com.ecommerce.project.ecommerce.dto.request.ProductRequest;
import com.ecommerce.project.ecommerce.entities.ProductEntity;
import com.ecommerce.project.ecommerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        productEntity.setDataUpdate(productDto.getDataUpdate());

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
             productDto.setDataUpdate(elemento.getDataUpdate());

             return productDto;
         }).collect(Collectors.toList());

         return  productDtos;
    }

    public ProductDto findById(Integer id) {
        ProductEntity productEntity = productRepository.findById(id).get();

        ProductDto productDto = new ProductDto();

        productDto.setNome(productEntity.getNome());
        productDto.setPreco(productEntity.getPreco());
        productDto.setQuantidade(productEntity.getQuantidade());
        productDto.setDescricao(productEntity.getDescricao());
        productDto.setAtivo(productEntity.isAtivo());
        productDto.setData(productEntity.getData());
        productDto.setDataUpdate(productEntity.getDataUpdate());

        return productDto;

    }

    public ProductDto updateById(Integer id, ProductRequest newData) {

        ProductEntity existingProduct = this.productRepository.findById(id).get();

        existingProduct.setNome(newData.getNome());
        existingProduct.setPreco(newData.getPreco());
        existingProduct.setQuantidade(newData.getQuantidade());
        existingProduct.setDescricao(newData.getDescricao());
        existingProduct.setAtivo(newData.isAtivo());
        existingProduct.setDataUpdate(LocalDate.now());

        productRepository.save(existingProduct);

        ProductDto productDto = new ProductDto();

        productDto.setNome(newData.getNome());
        productDto.setPreco(newData.getPreco());
        productDto.setQuantidade(newData.getQuantidade());
        productDto.setDescricao(newData.getDescricao());
        productDto.setAtivo(newData.isAtivo());
        productDto.setDataUpdate(existingProduct.getDataUpdate());

        return productDto;
    }






}
