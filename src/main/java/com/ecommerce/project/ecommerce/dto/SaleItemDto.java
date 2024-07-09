package com.ecommerce.project.ecommerce.dto;

public class SaleItemDto {

    private int id_produto;
    private int id_venda;
    private int quantidade;
    private double valor_total;

    public SaleItemDto(int id_produto, int id_venda, double valor_total, int quantidade) {
        this.id_produto = id_produto;
        this.id_venda = id_venda;
        this.valor_total = valor_total;
        this.quantidade = quantidade;
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

    public double getValor_total() {
        return valor_total;
    }
}
