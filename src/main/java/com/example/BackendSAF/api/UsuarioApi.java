package com.example.BackendSAF.api;

import com.example.BackendSAF.bl.UsuarioBl;
import com.example.BackendSAF.dto.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.sasl.AuthenticationException;


@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioApi {

    private final UsuarioBl usuarioBl;

    @Autowired
    public UsuarioApi(UsuarioBl usuarioBl) {
        this.usuarioBl = usuarioBl;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDto> login(
            @RequestParam(name = "user") String user,
            @RequestParam(name = "password") String password
    ) {
        try {
            // Lógica de autenticación aquí
            LoginDto loginDto = usuarioBl.login(user, password);
            return ResponseEntity.ok(loginDto);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }



}

