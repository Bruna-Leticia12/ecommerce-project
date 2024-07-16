package com.ecommerce.project.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Positive;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

//@Entity
//@Table(name = "tb_product")
public class Product implements Serializable {
//    @Serial
//    private static final long serialVersionUID = 1L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NotBlank(message = "Name is required")
//    private String name;
//
//    private String description;
//
//    @Positive(message = "Price must be positive")
//    private double price;
//
//    private int stockQuantity;
//
//    private boolean active = true;
//
//    @ManyToMany
//    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
//    private Set<Category> categories = new HashSet<>();
//
//    @OneToMany(mappedBy = "product")
//    private Set<SaleItem> saleItems = new HashSet<>();
//
//    @OneToMany(mappedBy = "product")
//    private List<Order> orders = new ArrayList<>();
//
//    public Product() {}
//
//    public Product(Long id, String name, String description, double price) {
//        this.id = id;
//        this.name = name;
//        this.description = description;
//        this.price = price;
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
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    public Set<SaleItem> getSaleItems() {
//        return saleItems;
//    }
//
//    public void setSaleItems(Set<SaleItem> saleItems) {
//        this.saleItems = saleItems;
//    }
//
//    public Set<Category> getCategories() {
//        return categories;
//    }
//
//    public void setCategories(Set<Category> categories) {
//        this.categories = categories;
//    }
//
//    public void setOrders(List<Order> orders) {
//        this.orders = orders;
//    }
//
//    public boolean isActive() {
//        return active;
//    }
//
//    public void setActive(boolean active) {
//        this.active = active;
//    }
//
//    public int getStockQuantity() {
//        return stockQuantity;
//    }
//
//    public void setStockQuantity(int stockQuantity) {
//        this.stockQuantity = stockQuantity;
//    }
//
//    public boolean hasEnoughStock(int quantity) {
//        return this.stockQuantity >= quantity;
//    }
//
//    public void decreaseStock(int quantity) {
//        if (hasEnoughStock(quantity)) {
//            this.stockQuantity -= quantity;
//        } else {
//            throw new RuntimeException("Insufficient stock");
//        }
//    }
//
//    @JsonIgnore
//    public Set<Order> getOrder() {
//        Set<Order> set = new HashSet<>();
//        for (SaleItem saleItem : saleItems) {
//            set.add(saleItem.getOrder());
//        }
//        return set;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Product product = (Product) o;
//        return Objects.equals(id, product.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
}
