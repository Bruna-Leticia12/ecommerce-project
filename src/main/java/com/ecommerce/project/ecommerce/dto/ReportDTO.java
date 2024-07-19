package com.ecommerce.project.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public class ReportDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant initialDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant finalDate;

    private Integer saleQuantity;
    private Double totalValueSale;
    private Integer saleWaitingPayment;
    private Double totalValueWaitingPayment;
    private Integer saleConfirmed;
    private Double totalValueConfirmed;
    private Integer saleCanceled;
    private Double totalValueCanceled;

    public ReportDTO(Instant initialDate, Instant finalDate, Integer saleQuantity, Double totalValueSale,
                     Integer saleWaitingPayment, Double totalValueWaitingPayment, Integer saleConfirmed,
                     Double totalValueConfirmed, Integer saleCanceled, Double totalValueCanceled) {
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.saleQuantity = saleQuantity;
        this.totalValueSale = totalValueSale;
        this.saleWaitingPayment = saleWaitingPayment;
        this.totalValueWaitingPayment = totalValueWaitingPayment;
        this.saleConfirmed = saleConfirmed;
        this.totalValueConfirmed = totalValueConfirmed;
        this.saleCanceled = saleCanceled;
        this.totalValueCanceled = totalValueCanceled;
    }

    public Instant getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Instant initialDate) {
        this.initialDate = initialDate;
    }

    public Instant getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Instant finalDate) {
        this.finalDate = finalDate;
    }

    public Integer getSaleQuantity() {
        return saleQuantity;
    }

    public void setSaleQuantity(Integer saleQuantity) {
        this.saleQuantity = saleQuantity;
    }

    public Double getTotalValueSale() {
        return totalValueSale;
    }

    public void setTotalValueSale(Double totalValueSale) {
        this.totalValueSale = totalValueSale;
    }

    public Integer getSaleWaitingPayment() {
        return saleWaitingPayment;
    }

    public void setSaleWaitingPayment(Integer saleWaitingPayment) {
        this.saleWaitingPayment = saleWaitingPayment;
    }

    public Double getTotalValueWaitingPayment() {
        return totalValueWaitingPayment;
    }

    public void setTotalValueWaitingPayment(Double totalValueWaitingPayment) {
        this.totalValueWaitingPayment = totalValueWaitingPayment;
    }

    public Integer getSaleConfirmed() {
        return saleConfirmed;
    }

    public void setSaleConfirmed(Integer saleConfirmed) {
        this.saleConfirmed = saleConfirmed;
    }

    public Double getTotalValueConfirmed() {
        return totalValueConfirmed;
    }

    public void setTotalValueConfirmed(Double totalValueConfirmed) {
        this.totalValueConfirmed = totalValueConfirmed;
    }

    public Integer getSaleCanceled() {
        return saleCanceled;
    }

    public void setSaleCanceled(Integer saleCanceled) {
        this.saleCanceled = saleCanceled;
    }

    public Double getTotalValueCanceled() {
        return totalValueCanceled;
    }

    public void setTotalValueCanceled(Double totalValueCanceled) {
        this.totalValueCanceled = totalValueCanceled;
    }
}