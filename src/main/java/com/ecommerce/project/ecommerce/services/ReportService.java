package com.ecommerce.project.ecommerce.services;

import com.ecommerce.project.ecommerce.dto.ReportDTO;
import com.ecommerce.project.ecommerce.entities.Sale;
import com.ecommerce.project.ecommerce.enums.SaleStatus;
import com.ecommerce.project.ecommerce.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private SaleRepository saleRepository;

    public ReportDTO monthReport(int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        Instant initialDate = start.atStartOfDay(ZoneOffset.UTC).toInstant();
        Instant finalDate = end.atTime(LocalTime.MAX).atZone(ZoneOffset.UTC).toInstant();

        return generateReport(initialDate, finalDate);
    }

    public ReportDTO weekReport(int year, int month, int day) {
        LocalDate start = LocalDate.of(year, month, day);
        LocalDate end = start.plusDays(6);
        Instant initialDate = start.atStartOfDay(ZoneOffset.UTC).toInstant();
        Instant finalDate = end.atTime(LocalTime.MAX).atZone(ZoneOffset.UTC).toInstant();

        return generateReport(initialDate, finalDate);
    }

    public ReportDTO queryDate(Instant initialDate, Instant finalDate) {
        return generateReport(initialDate, finalDate);
    }


    private ReportDTO generateReport(Instant initialDate, Instant finalDate) {
        List<Sale> sales = saleRepository.findBySaleDateBetween(initialDate, finalDate);

        int saleQuantity = sales.size();
        double totalValueSale = sales.stream().mapToDouble(Sale::getTotal).sum();

        int saleWaitingPayment = (int) sales.stream().filter(s -> s.getSaleStatus() == SaleStatus.WAITING_PAYMENT).count();
        double totalValueWaitingPayment = sales.stream().filter(s -> s.getSaleStatus() == SaleStatus.WAITING_PAYMENT).mapToDouble(Sale::getTotal).sum();

        int saleConfirmed = (int) sales.stream().filter(s -> s.getSaleStatus() == SaleStatus.CONFIRMED).count();
        double totalValueConfirmed = sales.stream().filter(s -> s.getSaleStatus() == SaleStatus.CONFIRMED).mapToDouble(Sale::getTotal).sum();

        int saleCanceled = (int) sales.stream().filter(s -> s.getSaleStatus() == SaleStatus.CANCELED).count();
        double totalValueCanceled = sales.stream().filter(s -> s.getSaleStatus() == SaleStatus.CANCELED).mapToDouble(Sale::getTotal).sum();

        return new ReportDTO(initialDate, finalDate, saleQuantity, totalValueSale,
                saleWaitingPayment, totalValueWaitingPayment, saleConfirmed, totalValueConfirmed,
                saleCanceled, totalValueCanceled);
    }
}