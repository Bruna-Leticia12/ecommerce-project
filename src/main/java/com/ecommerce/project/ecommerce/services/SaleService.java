package com.ecommerce.project.ecommerce.services;

import com.ecommerce.project.ecommerce.entities.Sale;
import com.ecommerce.project.ecommerce.repositories.SaleRepository;
import org.springframework.stereotype.Service;
import com.ecommerce.project.ecommerce.entities.User;

import com.ecommerce.project.ecommerce.services.exceptions.DatabaseException;
import com.ecommerce.project.ecommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    private SaleRepository repository;

    public SaleService(SaleRepository repository) {
        this.repository = repository;
    }

    public List<Sale> findAll() {
        return repository.findAll();
    }

    public Sale findById(Long id) {
        Optional<Sale> obj = repository.findById(id);
        return obj.get();
    }

//    @Transactional(readOnly = true)
//
//
//
//
//    @Transactional(readOnly = true)
//    public Sale findById(Long id) {
//        Optional<Sale> obj = repository.findById(id);
//        return obj.get();
////        return repository.findById(id)
////                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
//    }


//
//    @Transactional
//    public Sale insert(Sale obj) {
//        return saleRepository.save(obj);
//    }
//
//    @Transactional
//    public void delete(Long id) {
//        try {
//            saleRepository.deleteById(id);
//        } catch (EmptyResultDataAccessException e) {
//            throw new ResourceNotFoundException(id);
//        } catch (DataIntegrityViolationException e) {
//            throw new DatabaseException(e.getMessage());
//        }
//    }
//
//    @Transactional
//    public Sale update(Long id, Sale obj) {
//        try {
//            Sale entity = saleRepository.getOne(id);
//            updateData(entity, obj);
//            return saleRepository.save(entity);
//        } catch (EntityNotFoundException e) {
//            throw new ResourceNotFoundException(id);
//        }
//    }
//
//    private void updateData(Sale entity, Sale obj) {
//        entity.setSaleDate(obj.getSaleDate());
//        entity.setSaleStatus(obj.getSaleStatus());
//    }

//    //gerar pagamento de vendas
//    public Venda pagar(Integer vendaId) {
//                venda venda = repository.getReferenceById(vendaId);
//
//        if(venda.getStatusVenda() == StatusVenda.PENDENTE){
//            if(venda.getItens().size() > 0){
//                Instant dataPgto = Instant.now();
//                Pagamento pagamento = pagamentoService.criar(venda, dataPgto);
//                venda.setPagamento(pagamento);
//                venda.setStatusVenda(StatusVenda.FECHADA);
//                return repository.save(venda);
//            } else{
//        throw new EmptyOrderExeption(vendaId);
//    }else {
//            throw new UnavailableOrderException(vendaId);
//        }
//    }
//
//
//    //gerar rel mensal
//
//    public RelatorioDTO relatorioMensal (Integer mes, Integer ano){
//        YearMonth mesRelat = YearMonth.of(ano, mes);
//
//        LocalDateTime dataInicialLocal = mesRelat.atDay(1).atStartOfDay();
//        LocalDateTime dataFinalLocal = mesRelat.atEndOfMonth().atTime().atTime(23,59,59);
//
//        Instant dataInicial = dataInicialLocal.toInstant(ZoneOffset.UTC);
//        Instant dataFinal = dataFinalLocal.toInstant(ZoneOffset.UTC);
//
//        Relatorio dto = gerarRelatorio(dataInicial, dataFinal);
//
//        return dto;
//    }
//
//    //gerar rel semanal
//    public RelatorioDTO relatorioSemanal (Integer ano, Integer mes, Integer dia){
//        YearMonth mesRelat = YearMonth.of(ano, mes);
//
//        LocalDateTime dataConsulta = localDateTime.of(ano, mes, dia, 0, 0);
//
//        DayOfWeek diaDaSemana = dataConsulta.getDayOfWeek();
//
//        System.out.println(diaDaSemana);
//

}
