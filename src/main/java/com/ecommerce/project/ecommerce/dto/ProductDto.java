package com.ecommerce.project.ecommerce.dto;

import java.time.LocalDate;

public class ProductDto {

    private int id_categoria;
    private String nome;
    private double preco;
    private int quantidade;
    private String descricao;
    private boolean ativo;
    private LocalDate data;

    public ProductDto(int id_categoria, String nome, double preco, int quantidade, String descricao, boolean ativo, LocalDate data) {
        this.id_categoria = id_categoria;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.ativo = ativo;
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public String getNome() {
        return nome;
    }

    public int getId_categoria() {
        return id_categoria;
    }
}
