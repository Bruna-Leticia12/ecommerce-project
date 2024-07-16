//package com.ecommerce.project.ecommerce.entities;
//
//import com.ecommerce.project.ecommerce.entities.pk.SaleItemPK;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//
//import java.io.Serial;
//import java.io.Serializable;
//import java.util.Objects;
//
//@Entity
//@Table(name = "tb_sale_item")
//public class SaleItem implements Serializable {
//    @Serial
//    private static final long serialVersionUID = 1L;
//
//    @EmbeddedId
//    private SaleItemPK id = new SaleItemPK();
//
//    private Integer quantity;
//    private Double price;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("order_id")
//    @JoinColumn(name = "order_id", insertable = false, updatable = false)
//    private Order order;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("product_id")
//    @JoinColumn(name = "product_id", insertable = false, updatable = false)
//    private Product product;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "sale_id")
//    private Sale sale;
//
//    public SaleItem() {}
//
//    public SaleItem(Order order, Product product, int quantity, double price) {
//        this.order = order;
//        this.product = product;
//        this.quantity = quantity;
//        this.price = price;
//        this.id = new SaleItemPK(order.getId(), product.getId());
//    }
//
//    public SaleItemPK getId() {
//        return id;
//    }
//
//    public void setId(SaleItemPK id) {
//        this.id = id;
//    }
//
//    public Integer getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }
//
//    public Double getPrice() {
//        return price;
//    }
//
//    public void setPrice(Double price) {
//        this.price = price;
//    }
//
//    @JsonIgnore
//    public Order getOrder() {
//        return order;
//    }
//
//    public void setOrder(Order order) {
//        this.order = order;
//        if (id == null) {
//            id = new SaleItemPK();
//        }
//        id.setOrder_id(order.getId());
//    }
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//        if (id == null) {
//            id = new SaleItemPK();
//        }
//        id.setProduct_id(product.getId());
//    }
//
//    public double getSubTotal() {
//        return price * quantity;
//    }
//
//    public Sale getSale() {
//        return sale;
//    }
//
//    public void setSale(Sale sale) {
//        this.sale = sale;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        SaleItem saleItem = (SaleItem) o;
//        return Objects.equals(id, saleItem.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
//}