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
//
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
//    private Instant saleDate;
//
//    private Integer saleStatus;
//
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
//    private Instant saleDateUpdate;
//
//    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<SaleItem> items = new ArrayList<>();
//
//    @JsonIgnore
//    @OneToOne
//    @MapsId
//    private Order order;
//
//    public Sale() {}
//
//    public Sale(Long id, Instant saleDate, Integer saleStatus, Instant saleDateUpdate) {
//        this.id = id;
//        this.saleDate = saleDate;
//        this.saleStatus = saleStatus;
//        this.saleDateUpdate = saleDateUpdate;
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
//    public Integer getSaleStatus() {
//        return saleStatus;
//    }
//
//    public void setSaleStatus(Integer saleStatus) {
//        this.saleStatus = saleStatus;
//    }
//
//    public Instant getSaleDateUpdate() {
//        return saleDateUpdate;
//    }
//
//    public void setSaleDateUpdate(Instant saleDateUpdate) {
//        this.saleDateUpdate = saleDateUpdate;
//    }
//
//    public List<SaleItem> getItems() {
//        return items;
//    }
//
//    public void setItems(List<SaleItem> items) {
//        this.items = items;
//    }
//
//    public Order getOrder() {
//        return order;
//    }
//
//    public void setOrder(Order order) {
//        this.order = order;
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
//        return Objects.hash(id);
//    }
}