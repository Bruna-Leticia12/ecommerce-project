package com.ecommerce.project.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

public class SaleEntity {

    @JsonIgnore
    private int id;
    private int id_pedido;
    private LocalDate data;
    private LocalDate data_update;


    public SaleEntity(){}

    public SaleEntity(int id, int id_pedido, LocalDate data, LocalDate data_update) {
        this.id = id;
        this.id_pedido = id_pedido;
        this.data = data;
        this.data_update = data_update;
    }

    public int getId() {
        return id;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalDate getData_update() {
        return data_update;
    }
}
