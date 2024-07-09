package com.ecommerce.project.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity(name = "VENDAS")
public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public void setId(int id) {
        this.id = id;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setData_update(LocalDate data_update) {
        this.data_update = data_update;
    }
}
