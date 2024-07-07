package com.ecommerce.project.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.Date;

public class ProductEntity {

    @JsonIgnore
    private int id;
    private int id_categoria;
    private String nome;
    private double preco;
    private int quantidade;
    private String descricao;
    private boolean ativo;
    private LocalDate data;

    public ProductEntity(){}

    public ProductEntity(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public ProductEntity(int id, int id_categoria, String nome, double preco, int quantidade, String descricao, boolean ativo, LocalDate data) {
        this.id = id;
        this.id_categoria = id_categoria;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.ativo = ativo;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public LocalDate getData() {
        return data;
    }

}
