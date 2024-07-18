package com.ecommerce.project.ecommerce.entities;

import com.ecommerce.project.ecommerce.enums.SaleStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "tb_sale")
public class Sale implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant saleDate;
    private SaleStatus saleStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private User client;

    @OneToMany(mappedBy = "id.sale")
    private Set<SaleItem> items = new HashSet<>();

    @OneToOne(mappedBy = "sale", cascade = CascadeType.ALL)
    private Payment payment;

    public Sale() {
        this.saleStatus = SaleStatus.WAITING_PAYMENT;
    }

    public Sale(Integer id, Instant saleDate, User client) {
        super();
        this.id = id;
        this.saleDate = saleDate;
        this.saleStatus = SaleStatus.WAITING_PAYMENT;
        this.client = client;
    }

    public Sale(Integer id, Instant saleDate, SaleStatus saleStatus, User client) {
        super();
        this.id = id;
        this.saleDate = saleDate;
        this.saleStatus = saleStatus;
        //setSaleStatus(saleStatus);
        this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Instant saleDate) {
        this.saleDate = saleDate;
    }

    public SaleStatus getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(SaleStatus saleStatus) {
        if (saleStatus != null) {
            this.saleStatus = saleStatus;
        }
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Set<SaleItem> getItems() {
        return items;
    }

    public Double getTotal() {
        double sum = 0.0;
        for (SaleItem item : items) {
            sum += item.getSubTotal();
        }
        return sum;
    }

    public void setTotal(Double total) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return Objects.equals(id, sale.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", saleDate=" + saleDate +
                ", saleStatus=" + saleStatus +
                ", client=" + client +
                ", payment=" + payment +
                '}';
    }
}
