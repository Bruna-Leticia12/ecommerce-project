package com.ecommerce.project.ecommerce.entities;

import com.ecommerce.project.ecommerce.entities.pk.SaleItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_sale_item")
public class SaleItem implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private SaleItemPK id = new SaleItemPK();

    private Integer quantity;
    private Double price;

    public SaleItem() {}

    public SaleItem(Sale sale, Product product,Integer quantity, Double price) {
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
}