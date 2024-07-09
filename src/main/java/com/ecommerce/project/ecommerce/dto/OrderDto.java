package com.ecommerce.project.ecommerce.dto;

import java.time.LocalDate;

public class OrderDto {

    private int id_cliente;
    private double valor_total;
    private boolean enviado;
    private LocalDate data;


    public OrderDto(int id_cliente, double valor_total, boolean enviado, LocalDate data) {
        this.id_cliente = id_cliente;
        this.valor_total = valor_total;
        this.enviado = enviado;
        this.data = data;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public double getValor_total() {
        return valor_total;
    }

    public boolean isEnviado() {
        return enviado;
    }

    public LocalDate getData() {
        return data;
    }
}
