package com.ecommerce.project.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SaleItemEntity {

    @JsonIgnore
    private int id;
    private int id_produto;
    private int id_venda;
    private int quantidade;
    private double valor_total;

    public SaleItemEntity() {};

    public SaleItemEntity(int id, int id_produto, int id_venda, int quantidade, double valor_total) {
        this.id = id;
        this.id_produto = id_produto;
        this.id_venda = id_venda;
        this.quantidade = quantidade;
        this.valor_total = valor_total;
    }

    public int getId() {
        return id;
    }

    public int getId_produto() {
        return id_produto;
    }

    public int getId_venda() {
        return id_venda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double valor_total (){
        return valor_total;
    }
}
