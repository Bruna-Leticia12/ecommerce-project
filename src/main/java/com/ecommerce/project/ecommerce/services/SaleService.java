package com.ecommerce.project.ecommerce.services;

import com.ecommerce.project.ecommerce.dto.ProductDto;
import com.ecommerce.project.ecommerce.dto.SaleDto;
import com.ecommerce.project.ecommerce.dto.request.ProductRequest;
import com.ecommerce.project.ecommerce.dto.request.SaleRequest;
import com.ecommerce.project.ecommerce.entities.ProductEntity;
import com.ecommerce.project.ecommerce.entities.SaleEntity;
import com.ecommerce.project.ecommerce.enums.SaleStatus;
import com.ecommerce.project.ecommerce.repositories.SaleRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<SaleDto> getAllSales() {

        List<SaleEntity> saleEntities = saleRepository.findAll();
        List<SaleDto> saleDtos = saleEntities.stream().map(elemento -> {
            SaleDto saleDto = new SaleDto();

            saleDto.setIdPedido(elemento.getIdPedido());
            saleDto.setSaleStatus(elemento.getSaleStatus());
            saleDto.setData(elemento.getData());
            saleDto.setData_update(elemento.getData_update());

            return saleDto;

        }).collect(Collectors.toList());

        return saleDtos;
    }

    public SaleDto findById(Integer id) {
        SaleEntity saleEntity = saleRepository.findById(id).get();

        SaleDto saleDto = new SaleDto();

        saleDto.setIdPedido(saleEntity.getIdPedido());
        saleDto.setSaleStatus(saleEntity.getSaleStatus());
        saleDto.setData(saleEntity.getData());
        saleDto.setData_update(saleEntity.getData_update());

        return saleDto;
    }

    public String confirmSale(Integer id) {

        SaleEntity existingSale = this.saleRepository.findById(id).get();

        existingSale.setSaleStatus(SaleStatus.CONFIRMADA);

        saleRepository.save(existingSale);

        return "Venda Confirmada";
    }

    public String cancelSale(Integer id) {

        SaleEntity existingSale = this.saleRepository.findById(id).get();

        existingSale.setSaleStatus(SaleStatus.CANCELADA);

        saleRepository.save(existingSale);

        return "Venda Cancelada";
    }

    public void deleteById(Integer id) {

        saleRepository.deleteById(id);
    }

}
