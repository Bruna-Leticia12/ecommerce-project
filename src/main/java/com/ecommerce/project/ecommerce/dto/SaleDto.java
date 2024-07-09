package com.ecommerce.project.ecommerce.dto;

import java.time.LocalDate;

public class SaleDto {

    private int id_pedido;
    private LocalDate data;
    private LocalDate data_update;

    public SaleDto(int id_pedido, LocalDate data, LocalDate data_update) {
        this.id_pedido = id_pedido;
        this.data = data;
        this.data_update = data_update;
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
