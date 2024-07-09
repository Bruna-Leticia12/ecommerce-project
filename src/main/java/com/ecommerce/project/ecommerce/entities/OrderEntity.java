package com.ecommerce.project.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity(name = "PEDIDOS")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public void setId(int id) {
        this.id = id;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }

    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
