package com.ecommerce.project.ecommerce.dto.request;

public class SaleRequest {

    private int idPedido;

    public SaleRequest() {
    }

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
