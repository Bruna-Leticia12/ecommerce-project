package com.ecommerce.project.ecommerce.services;

import com.ecommerce.project.ecommerce.entities.Sale;
import com.ecommerce.project.ecommerce.repositories.SaleRepository;
import com.ecommerce.project.ecommerce.services.exceptions.DatabaseException;
import com.ecommerce.project.ecommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    private SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Transactional(readOnly = true)
    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Sale findById(Long id) {
        return saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found with id " + id));
    }

    @Transactional
    public Sale insert(Sale obj) {
        return saleRepository.save(obj);
    }

    @Transactional
    public void delete(Long id) {
        Sale sale = findById(id);
        saleRepository.delete(sale);
    }

    @Transactional
    public Sale update(Long id, Sale obj) {
        Sale entity = findById(id);
        updateData(entity, obj);
        return saleRepository.save(entity);
    }

    private void updateData(Sale entity, Sale obj) {
        entity.setSaleDateUpdate(obj.getSaleDateUpdate());
        entity.setSaleStatus(obj.getSaleStatus());
    }

    @Transactional(readOnly = true)
    public List<Sale> findBySaleDateBetween(Instant startDate, Instant endDate) {
        return saleRepository.findBySaleDateBetween(startDate, endDate);
    }

    @Transactional(readOnly = true)
    public List<Sale> generateMonthlySalesReport() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime firstDayOfMonth = now.with(TemporalAdjusters.firstDayOfMonth()).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime lastDayOfMonth = now.with(TemporalAdjusters.lastDayOfMonth()).withHour(23).withMinute(59).withSecond(59).withNano(999);
        return findBySaleDateBetween(firstDayOfMonth.atZone(ZoneId.systemDefault()).toInstant(),
                lastDayOfMonth.atZone(ZoneId.systemDefault()).toInstant());
    }

    @Transactional(readOnly = true)
    public List<Sale> generateWeeklySalesReport() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime firstDayOfWeek = now.with(DayOfWeek.MONDAY).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime lastDayOfWeek = now.with(DayOfWeek.SUNDAY).withHour(23).withMinute(59).withSecond(59).withNano(999);
        return findBySaleDateBetween(firstDayOfWeek.atZone(ZoneId.systemDefault()).toInstant(),
                lastDayOfWeek.atZone(ZoneId.systemDefault()).toInstant());
    }
}
