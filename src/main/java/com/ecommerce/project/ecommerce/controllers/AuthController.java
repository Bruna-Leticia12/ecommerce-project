package com.ecommerce.project.ecommerce.controllers;

import com.ecommerce.project.ecommerce.dto.LoginRequestDTO;
import com.ecommerce.project.ecommerce.dto.RegisterRequestDTO;
import com.ecommerce.project.ecommerce.dto.ResponseDTO;
import com.ecommerce.project.ecommerce.dto.SuccessResponseDTO;
import com.ecommerce.project.ecommerce.entities.User;
import com.ecommerce.project.ecommerce.infra.security.TokenService;
import com.ecommerce.project.ecommerce.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequestDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new ResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterRequestDTO data) {
        if (this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(), data.login(), data.phone(), encryptedPassword, data.role());
        this.repository.save(newUser);
        SuccessResponseDTO response = new SuccessResponseDTO("Registro realizado com sucesso!");

        return ResponseEntity.ok(response);
    }
}
