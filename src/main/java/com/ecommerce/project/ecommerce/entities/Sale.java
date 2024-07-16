package com.ecommerce.project.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//@Entity
//@Table(name = "tb_sale")
public class Sale implements Serializable {
//    @Serial
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    //
////    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
//    private Instant saleDate;
//
//    @ManyToOne
//    @JoinColumn(name = "client_id")
//    private User client;
//
//    public Sale() {
//    }
//
//    public Sale(Long id, Instant saleDate, User client) {
//        this.id = id;
//        this.saleDate = saleDate;
//        this.client = client;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Instant getSaleDate() {
//        return saleDate;
//    }
//
//    public void setSaleDate(Instant saleDate) {
//        this.saleDate = saleDate;
//    }
//
//    public User getClient() {
//        return client;
//    }
//
//    public void setClient(User client) {
//        this.client = client;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Sale sale = (Sale) o;
//        return Objects.equals(id, sale.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hashCode(id);
//    }
//}

//        private Integer saleStatus;
//
//    @ManyToOne
//    @JoinColumn(name = "client_id", referencedColumnName = "id")
//    private User client;
//
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//    private Set<SaleItem> items;
//
//    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
//    private Sale sale;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;
//
//    public Order() {}
//
//    public Order(Long id, Instant shippingDate, Integer saleStatus, User client) {
//        this.id = id;
//        this.shippingDate = shippingDate;
//        this.saleStatus = saleStatus;
//        this.client = client;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Instant getShippingDate() {
//        return shippingDate;
//    }
//
//    public void setShippingDate(Instant shippingDate) {
//        this.shippingDate = shippingDate;
//    }
//
//    public User getClient() {
//        return client;
//    }
//
//    public void setClient(User client) {
//        this.client = client;
//    }
//
//    public Integer getSaleStatus() {
//        return saleStatus;
//    }
//
//    public void setSaleStatus(Integer saleStatus) {
//        this.saleStatus = saleStatus;
//    }
//
//    public Set<SaleItem> getItems() {
//        return items;
//    }
//
//    public void setItems(Set<SaleItem> items) {
//        this.items = items;
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
//    public Double getTotal() {
//        double sum = 0.0;
//        for (SaleItem item : items) {
//            sum += item.getSubTotal();
//        }
//        return sum;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Order order = (Order) o;
//        return Objects.equals(id, order.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
}