package com.ecommerce.project.ecommerce.controllers;

import com.ecommerce.project.ecommerce.dto.LoginRequestDTO;
import com.ecommerce.project.ecommerce.dto.RegisterRequestDTO;
import com.ecommerce.project.ecommerce.dto.ResponseDTO;
import com.ecommerce.project.ecommerce.entities.User;
import com.ecommerce.project.ecommerce.enums.UserRole;
import com.ecommerce.project.ecommerce.infra.security.TokenService;
import com.ecommerce.project.ecommerce.repositories.UserRepository;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
//@RequiredArgsConstructor
public class AuthController {

    //private final PasswordEncoder passwordEncoder;

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

        var token = tokenService.generateToken((User)auth.getPrincipal());

        return ResponseEntity.ok(new ResponseDTO(token));
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterRequestDTO data){
        if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(), data.login(), data.phone(),  encryptedPassword, data.role());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}


//        Optional<User> user = this.repository.findByEmail(body.email());


//        if(user.isEmpty()) {
//            User newUser = new User();
//            newUser.setPassword(passwordEncoder.encode(body.password()));
//            newUser.setName(body.name());
//            newUser.setEmail(body.email());
//            newUser.setPhone(body.phone());
//            this.repository.save(newUser);
//
//            String token = this.tokenService.generateToken(newUser);
//            return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
//        }
//        return ResponseEntity.badRequest().build();
//    }


//@PostMapping("/login")
//public ResponseEntity login(@RequestBody LoginRequestDTO body){
//    User user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
//    if(passwordEncoder.matches(body.password(), user.getPassword())) {
//        String token = this.tokenService.generateToken(user);
//        return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
//    }
//    return ResponseEntity.badRequest().build();
//}
//
//
//@PostMapping("/register")
//public ResponseEntity register(@RequestBody RegisterRequestDTO body){
//    Optional<User> user = this.repository.findByEmail(body.email());
//
//    if(user.isEmpty()) {
//        User newUser = new User();
//        newUser.setPassword(passwordEncoder.encode(body.password()));
//        newUser.setName(body.name());
//        newUser.setEmail(body.email());
//        newUser.setPhone(body.phone());
//        this.repository.save(newUser);
//
//        String token = this.tokenService.generateToken(newUser);
//        return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
//    }
//    return ResponseEntity.badRequest().build();
//}