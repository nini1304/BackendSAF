package com.example.BackendSAF.api;

import com.example.BackendSAF.bl.UsuarioBl;
import com.example.BackendSAF.dto.EmpresaDto;
import com.example.BackendSAF.dto.LoginDto;
import com.example.BackendSAF.dto.RolDto;
import com.example.BackendSAF.dto.UsuarioDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioApi.class);
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
    @PostMapping ("/registrar")
    public ResponseEntity<UsuarioDto> registrar(
            @RequestParam(name = "nombre") String nombre,
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "idRol") Long idRol,
            @RequestParam(name = "idEmpresa") Long idEmpresa
    ) throws ParseException {
        UsuarioDto usuarioDto = usuarioBl.registrar(nombre, username, password, idRol, idEmpresa);
        return ResponseEntity.ok(usuarioDto);
    }

    @GetMapping("/listar")
    public Object listarUsuarios(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        LOGGER.info("Ejecutando listarUsuarios...");
        return usuarioBl.list(page, size);
    }

    @GetMapping("/empresa")
    public List<EmpresaDto> listarEmpresas() {
        return usuarioBl.getEmpresa();
    }

    @GetMapping("/rol")
    public List<RolDto> listarRoles() {
        return usuarioBl.getRoles();
    }
}

