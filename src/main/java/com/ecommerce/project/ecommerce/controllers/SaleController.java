package com.ecommerce.project.ecommerce.controllers;

import com.ecommerce.project.ecommerce.entities.Sale;
import com.ecommerce.project.ecommerce.services.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

    private SaleService service;

    public SaleController(SaleService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Sale>> findAll(){
        List<Sale> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Sale> findById(@PathVariable Long id){
        Sale obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
//
//    //listar vendas entre datas
//    @PostMapping(value = "/consulta-entre-datas")
//    public ResponseEntity<List<Sale>> consultaEntreDatas(@RequestBody)
//        List<Sale> list = service.consultaEntreDatas(dto);
//        return ResponseEntity.ok().body(list);
//    }
//
////criar nova venda
//@PostMapping(value = "/criar-venda")
//public ResponseEntity<List<Sale>> consultaEntreDatas(@RequestParam (value = ))
//Venda venda = service.criar(clienteId);
//URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//        return ResponseEntity.creaty(uri).body(venda);
//    }
//
//
//    //inserir item na venda
//    @PostMapping(value = "{vendaId}/inserir-item")
//    public ResponseEntity<Venda> inserirItem(@PathVariable Integer);
//        return ResponseEntity.ok().body(service.inserirItem())
//    }
//
////retirar item na venda
//@DeleteMapping(value = "{vendaId}/deletar-item/{productId}")
//public ResponseEntity<Venda> retirarItemVenda(@PathVariable );
//        Venda venda = service.retirarItemVenda(vendaId, productId)
//        return ResponseEntity.ok().body(venda);
//        }
//
////atualizar quantidade item na venda
//@PutMapping(value = "{vendaId}/atualizar-qnt-item")
//public ResponseEntity<Venda> atualizarQuantidade(@PathVariable );
//Venda venda = service.atualizarItemVenda(vendaId, dto)
//        return ResponseEntity.ok().body(venda);
//        }
//
//
//
//    //cancelar uma venda
//    @PutMapping(value = "/{id}/cancelar")
//public ResponseEntity<Sale> calcelar(@PathVariable Integer id){
//    Sale sale = service.cancelarVenda(id);
//    return ResponseEntity.ok().body(sale);
//    }
//
//    //gerar pagamento de uma venda
//@PutMapping(value = "/{id}/pagar")
//public ResponseEntity<Sale> pagar(@PathVariable Integer id){
//    Sale sale = service.pagar(id);
//    return ResponseEntity.ok().body(sale);
//}
//
////gerar relatorio mensal
//@GetMapping(value = "/relatorio-mensal/{ano}/{mes}")
//public ResponseEntity<RelatorioDTO> relatorioMensal(@PathVariable Integer ano, @PathVariable Integer mes);
//    System.out.println("1");
//    RelatorioDTO dto = service.relatorioMensal(mes, ano);
//    return ResponseEntity.ok().body(dto);
//    }
//
////gerar relatorio semanal
//@GetMapping(value = "/relatorio-semanal/{ano}/{mes}/{dia}")
//public ResponseEntity<RelatorioDTO> relatorioSemanal(@PathVariable Integer ano, @PathVariable Integer mes, @PathVariable Integer dia);
//RelatorioDTO dto = service.relatorioSemanal(mes, ano, dia);
//    return ResponseEntity.ok().body(dto);
//    }

    //olhar dpoisss

//
//    @PostMapping
//    public ResponseEntity<Sale> insert(@RequestBody Sale obj){
//        obj = service.insert(obj);
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
//        return ResponseEntity.created(uri).body(obj);
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id){
//        service.delete(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    @PutMapping(value = "/{id}")
//    public ResponseEntity<Sale> update(@PathVariable Long id, @RequestBody Sale obj){
//        obj = service.update(id, obj);
//        return ResponseEntity.ok().body(obj);
//    }
}
