package com.ecommerce.project.ecommerce.services;

import com.ecommerce.project.ecommerce.dto.SaleDto;
import com.ecommerce.project.ecommerce.entities.SaleEntity;
import com.ecommerce.project.ecommerce.repositories.SaleRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class SaleService {

    private SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public SaleDto createSale(SaleDto saleDto) {

        SaleEntity saleEntity = new SaleEntity();

        saleEntity.setId_pedido(saleDto.getId_pedido());
        saleEntity.setData(saleDto.getData());
        saleEntity.setData_update((saleDto.getData_update()));

        saleRepository.save(saleEntity);

        return saleDto;

    }
}
