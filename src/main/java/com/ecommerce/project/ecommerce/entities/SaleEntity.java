package com.ecommerce.project.ecommerce.entities;

import com.ecommerce.project.ecommerce.enums.SaleStatus;
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
    private int idPedido;
    private SaleStatus saleStatus;
    private LocalDate data;
    private LocalDate data_update;


    public SaleEntity(){}

    public SaleEntity(int id, int idPedido, SaleStatus saleStatus, LocalDate data, LocalDate data_update) {
        this.id = id;
        this.idPedido = idPedido;
        this.saleStatus = saleStatus;
        this.data = data;
        this.data_update = data_update;
    }

    public int getId() {
        return id;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public SaleStatus getSaleStatus() {
        return saleStatus;
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

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public void setSaleStatus(SaleStatus saleStatus) {
        this.saleStatus = saleStatus;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setData_update(LocalDate data_update) {
        this.data_update = data_update;
    }
}
