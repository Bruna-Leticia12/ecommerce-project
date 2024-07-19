package com.ecommerce.project.ecommerce.services;

import com.ecommerce.project.ecommerce.entities.Product;
import com.ecommerce.project.ecommerce.entities.Sale;
import com.ecommerce.project.ecommerce.entities.SaleItem;
import com.ecommerce.project.ecommerce.entities.pk.SaleItemPK;
import com.ecommerce.project.ecommerce.repositories.SaleItemRepository;
import com.ecommerce.project.ecommerce.services.exceptions.DatabaseException;
import com.ecommerce.project.ecommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleItemService {

    @Autowired
    private SaleItemRepository repository;


    // Para consultar venda
    public List<SaleItem> findAll() {
        return repository.findAll();
    }

    public SaleItem findById(SaleItemPK id) {
        Optional<SaleItem> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    // Para criar itemVenda
    public SaleItem insert(Sale sale, Product product, Integer quantity) {
        SaleItem saleItem = new SaleItem();
        saleItem.setSale(sale);
        saleItem.setProduct(product);
        saleItem.setQuantity(quantity);
        saleItem.setPrice(product.getPrice());
        return repository.save(saleItem);
    }

    // Para deletar itemVenda								so posso deletar se o status estiver pendente
    public void delete(SaleItemPK saleItemPK) {
        try {
            if (repository.existsById(saleItemPK)) {
                repository.deleteById(saleItemPK);
            } else {
                throw new ResourceNotFoundException(saleItemPK);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    // Para atualizar itemVenda
    public SaleItem updateData(SaleItemPK itemVendaPK, SaleItem obj) {
        try {
            SaleItem entity = repository.getReferenceById(itemVendaPK);
            updateItems(entity, obj);
            return repository.save(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(itemVendaPK);
        }

    }

    private void updateItems(SaleItem entity, SaleItem obj) {
        entity.setQuantity(obj.getQuantity());
        entity.setPrice(obj.getPrice());
    }

}
