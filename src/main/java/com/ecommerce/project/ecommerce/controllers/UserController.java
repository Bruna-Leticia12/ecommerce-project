package com.ecommerce.project.ecommerce.controllers;

import com.ecommerce.project.ecommerce.dto.UserUpdateDTO;
import com.ecommerce.project.ecommerce.entities.User;
import com.ecommerce.project.ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService service;

    //Listar todos os usuarios
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    //Listar o usuario por id
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id) {
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    //Inserir um usuario
    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    //Deletar um usuario
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    //Atualizar um usuario
    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable Integer id, @RequestBody UserUpdateDTO dto) {
        User client = service.update(id, dto);
        return ResponseEntity.ok().body(client);
    }
}
