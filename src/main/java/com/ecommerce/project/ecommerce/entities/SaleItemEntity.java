package com.ecommerce.project.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "ITENSVENDAS")
public class SaleItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public void setId(int id) {
        this.id = id;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public void setId_venda(int id_venda) {
        this.id_venda = id_venda;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }
}
