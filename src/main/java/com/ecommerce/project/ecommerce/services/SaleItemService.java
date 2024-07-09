package com.ecommerce.project.ecommerce.services;

import com.ecommerce.project.ecommerce.dto.SaleItemDto;
import com.ecommerce.project.ecommerce.entities.SaleItemEntity;
import com.ecommerce.project.ecommerce.repositories.SaleItemRepository;
import org.springframework.stereotype.Service;

@Service
public class SaleItemService {

    public SaleItemRepository saleItemRepository;

    public SaleItemService(SaleItemRepository saleItemRepository) {
        this.saleItemRepository = saleItemRepository;
    }

    public SaleItemDto createSaleItem(SaleItemDto saleItemDto){

        SaleItemEntity saleItemEntity = new SaleItemEntity();

        saleItemEntity.setId_produto(saleItemDto.getId_produto());
        saleItemEntity.setId_venda(saleItemDto.getId_venda());
        saleItemEntity.setQuantidade(saleItemDto.getQuantidade());
        saleItemEntity.setValor_total(saleItemDto.getValor_total());

        saleItemRepository.save(saleItemEntity);

        return saleItemDto;
    }
}
