package com.example.BackendSAF.dto;

import com.example.BackendSAF.entity.RolDao;
import com.example.BackendSAF.entity.UsuarioEmpresaDao;

import java.util.List;

public class UsuarioListDto {
    private Long id;
    private String nombre;
    private String username;
    private String password;
    private String rolNombre;

    public UsuarioListDto() {
    }

    public UsuarioListDto(Long id, String nombre, String username, String password, String rolNombre) {
        this.id = id;
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.rolNombre = rolNombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }
}
