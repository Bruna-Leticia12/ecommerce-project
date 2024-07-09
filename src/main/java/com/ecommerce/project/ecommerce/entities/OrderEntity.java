package com.ecommerce.project.ecommerce.entities;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

public class OrderEntity {

    @JsonIgnore
    private int id;
    private int id_cliente;
    private double valor_total;
    private boolean enviado;
    private LocalDate data;

    public OrderEntity(){};

    public OrderEntity(int id, int id_cliente, double valor_total, boolean enviado, LocalDate data) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.valor_total = valor_total;
        this.enviado = enviado;
        this.data = data;
    }

    public int getId(){
        return id;
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
