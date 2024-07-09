package com.ecommerce.project.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CategoryEntity {

    @JsonIgnore
    private int id;
    private String nome;
    private boolean ativo;

    public CategoryEntity() {
    }

    public CategoryEntity(int id, String nome, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.ativo = ativo;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public boolean isAtivo() {
        return ativo;
    }
}
