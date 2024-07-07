package com.ecommerce.project.ecommerce.dto;

import java.time.LocalDate;

public class ProductDto {

    private String nome;
    private double preco;

    public ProductDto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }
}
