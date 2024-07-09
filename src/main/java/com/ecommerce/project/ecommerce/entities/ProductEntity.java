package com.ecommerce.project.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity(name = "PRODUTOS")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int id_categoria;
    private String nome;
    private double preco;
    private int quantidade;
    private String descricao;
    private boolean ativo;
    private LocalDate data;
    private LocalDate dataUpdate;

    public ProductEntity(){}

    public ProductEntity(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public ProductEntity(int id, int id_categoria, String nome, double preco, int quantidade, String descricao, boolean ativo, LocalDate data, LocalDate dataUpdate) {
        this.id = id;
        this.id_categoria = id_categoria;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.ativo = ativo;
        this.data = data;
        this.dataUpdate = dataUpdate;
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

    public LocalDate getDataUpdate() {
        return dataUpdate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setDataUpdate(LocalDate dataUpdate) {
        this.dataUpdate = dataUpdate;
    }
}
