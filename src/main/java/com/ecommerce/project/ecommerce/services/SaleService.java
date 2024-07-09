package com.ecommerce.project.ecommerce.services;

import com.ecommerce.project.ecommerce.dto.SaleDto;
import com.ecommerce.project.ecommerce.dto.request.SaleRequest;
import com.ecommerce.project.ecommerce.entities.SaleEntity;
import com.ecommerce.project.ecommerce.enums.SaleStatus;
import com.ecommerce.project.ecommerce.repositories.SaleRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class SaleService {

    private SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public SaleDto createSale(SaleRequest saleRequest) {

        LocalDate date = LocalDate.now();
        SaleEntity saleEntity = new SaleEntity();

        saleEntity.setIdPedido(saleRequest.getIdPedido());
        saleEntity.setSaleStatus(SaleStatus.PROCESSANDO);
        saleEntity.setData(date);
        saleEntity.setData_update(date);

        saleRepository.save(saleEntity);

        SaleDto saleDto = new SaleDto(
                saleEntity.getIdPedido(),
                saleEntity.getSaleStatus(),
                saleEntity.getData(),
                saleEntity.getData_update());

        return saleDto;

    }
}
