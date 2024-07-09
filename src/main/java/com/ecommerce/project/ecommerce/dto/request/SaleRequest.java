package com.ecommerce.project.ecommerce.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SaleRequest {

    private int idPedido;

    public SaleRequest() {}

    public SaleRequest(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
}
