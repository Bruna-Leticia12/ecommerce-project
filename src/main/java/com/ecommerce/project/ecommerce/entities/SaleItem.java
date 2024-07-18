package com.ecommerce.project.ecommerce.entities;

import com.ecommerce.project.ecommerce.entities.pk.SaleItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "tb_sale_item")
public class SaleItem implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private SaleItemPK id = new SaleItemPK();

    @PositiveOrZero
    @NotNull
    private Integer quantity;

    @PositiveOrZero
    @NotNull
    private Double price;
    private double subTotal;

    public SaleItem() {}

    public SaleItem(Sale sale, Product product,Integer quantity, Double price) {
        super();
        id.setSale(sale);
        id.setProduct(product);
        this.quantity = quantity;
        this.price = price;
    }

    @JsonIgnore
    public Sale getSale(){
        return id.getSale();
    }

    public void setSale(Sale sale){
        id.setSale(sale);
    }

    public Product getProduct(){
        return id.getProduct();
    }

    public void setProduct(Product product){
        id.setProduct(product);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSubTotal(){
        return price * quantity;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleItem saleItem = (SaleItem) o;
        return Objects.equals(id, saleItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "SaleItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", price=" + price +
                ", subTotal=" + subTotal +
                '}';
    }

}